package com.spaceRangers.controller.api;

import com.spaceRangers.entities.ComplainEntity;
import com.spaceRangers.entities.UserAccountEntity;
import com.spaceRangers.repository.ComplainRepository;
import com.spaceRangers.repository.UserRepository;
import com.spaceRangers.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/api/complain")
public class ComplainController {

    @Autowired
    ComplainService complainService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ComplainRepository complainRepository;

    @Secured("ROLE_USER")
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createComplain(@RequestBody ComplainEntity complainEntity){
        try {
            return ResponseEntity.ok(complainService.createComplain(complainEntity));
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getComplains(
            @RequestParam(required = false) boolean sortByDate,
            @RequestParam(required = false) boolean sortByStatus
    ){
        Stream<ComplainEntity> stream = complainService.getComplains().stream();
        stream = (sortByDate) ? stream.sorted(Comparator.comparing(ComplainEntity::getDate)) : stream;
        stream = (sortByStatus) ? stream.sorted(Comparator.comparing(complainEntity -> complainEntity.getStateComplain().getName())) : stream;
        return ResponseEntity.ok(stream.collect(Collectors.toList()));
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateComplain(@RequestBody ComplainEntity complainEntity){
        try {
            complainService.updateComplain(complainEntity);
            return ResponseEntity.ok().build();
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity processiongComplain(
            @RequestBody ComplainEntity complainEntity,
            @AuthenticationPrincipal User user,
            @PathVariable int id

            ){

        Optional<ComplainEntity> complainEntityOptional = complainRepository.findById(id);

        if(complainEntityOptional.isPresent()){
            return ResponseEntity.ok(complainService.startProcessionComplain(complainEntity, user));
        }else return ResponseEntity.notFound().build();
    }



}
