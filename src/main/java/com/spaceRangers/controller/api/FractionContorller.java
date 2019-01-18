package com.spaceRangers.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceRangers.config.websocket.exceptions.NotEnoughMoneyException;
import com.spaceRangers.entities.*;
import com.spaceRangers.impl.FilterService;
import com.spaceRangers.repository.StateUserFractionRepository;
import com.spaceRangers.repository.UserFractionRepository;
import com.spaceRangers.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/fraction")
public class FractionContorller {

    @Autowired
    FractionService fractionService;

    @Autowired
    FilterService filterService;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    AdvisersPlayerFractionService advisersPlayerFractionService;

    @Autowired
    PlayerFractionService playerFractionService;

    @Autowired
    LeaderPlayerFractionService leaderPlayerFractionService;

    @Autowired
    StateUserFractionRepository stateUserFractionRepository;

    @Autowired
    UserFractionRepository userFractionRepository;


    @Secured("ROLE_USER")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllFractions(
            @RequestParam(required = false) boolean sortByName,
            @RequestParam(required = false) boolean sortByCountPeople
    ){
            Stream<FractionEntity> stream = fractionService.getListFractions().stream();
            stream = (sortByName) ? stream.sorted(Comparator.comparing(FractionEntity::getNameFraction)) : stream;
            stream = (sortByCountPeople) ? stream.sorted(Comparator.comparing(fractionEntity -> fractionEntity.getUsersFraction().size())) : stream;

            return ResponseEntity.ok(stream.collect(Collectors.toList()));
    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createFracion(
            @AuthenticationPrincipal User user,
            @RequestBody FractionEntity fraction
            ){
            UsersEntity usersEntity = registrationService.getUser(user);

        FractionEntity fractionEntity = null;
        try {
            fractionEntity = fractionService.createFraction(fraction, usersEntity);
            return ResponseEntity.ok(fractionEntity);
        } catch (NotEnoughMoneyException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }



    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFraction(
            @PathVariable int id
    ){
            return ResponseEntity.ok(fractionService.getFraction(id));
    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFractionTasks(
            @PathVariable int id,
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) boolean sortByName,
            @RequestParam(required = false) boolean sortByType,
            @RequestParam(required = false) boolean sortByState,
            @RequestParam(required = false) boolean sortByPrivacy
    ){
            FractionEntity fraction = fractionService.getFraction(id);

            Stream<TaskEntity> stream = fraction.getTasks()
                    .stream()
                    .filter(e->filterService.taskFilter(fraction, e, user));

            stream = (sortByName) ? stream.sorted(Comparator.comparing(TaskEntity::getName)) : stream;
            stream = (sortByType) ? stream.sorted(Comparator.comparing(taskEntity -> taskEntity.getTypeTask().getName())) : stream;
            stream = (sortByState) ? stream.sorted(Comparator.comparing(taskEntity -> taskEntity.getStateTask().getName())) : stream;
            stream = (sortByPrivacy) ? stream.sorted(Comparator.comparing(taskEntity -> taskEntity.getStatePrivacy().getName())) : stream;

            return ResponseEntity.ok(stream.collect(Collectors.toList()));
    }




    @Secured({
            "ROLE_LEADER",
            "ROLE_ADVISER"
    })
    @RequestMapping(value = "/{id}/tasks", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTask(
            @AuthenticationPrincipal User user,
            @PathVariable int id,
            @RequestBody TaskEntity task
    ){
            task.setId(null);

            try{
                FractionEntity fraction = fractionService.getFraction(id);

                task.setFraction(fraction);
                advisersPlayerFractionService.createTask(task);
                return ResponseEntity.ok(task);

            }catch (NullPointerException e){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
    }



    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/tasks/{idTask}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTaskById(
            @AuthenticationPrincipal User user,
            @PathVariable int id,
            @PathVariable int idTask
    ){
            FractionEntity fraction = fractionService.getFraction(id);

            TaskEntity taskEntity = fractionService.getFractionTask(idTask);

            if(filterService.taskFilter(fraction, taskEntity, user)) return ResponseEntity.ok(taskEntity);
            else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @Secured("ROLE_LEADER")
    @RequestMapping(value = "/{id}/tasks/{idTask}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateTask(
            @AuthenticationPrincipal User user,
             @PathVariable int id,
             @PathVariable int idTask,
            @RequestBody TaskEntity task
    ){
            try{
                FractionEntity fraction = fractionService.getFraction(id);
                if(filterService.taskInFraction(fraction, task)) {
                    task.setFraction(fraction);
                    leaderPlayerFractionService.updateTask(task);
                    return ResponseEntity.ok(task);
                }else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }catch (NullPointerException e){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }

    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/join", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity subscribeUser(
        @AuthenticationPrincipal User user,
        @PathVariable int id
    ){
            FractionEntity fraction = fractionService.getFraction(id);
                UsersEntity usersEntity = registrationService.getUser(user);

            try {
                UserFractionEntity userFraction = fractionService.joinFraction(fraction, usersEntity);

                if(userFraction == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                return ResponseEntity.ok(userFraction);

            }catch (NullPointerException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "/out", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity outFromFraction(
            @AuthenticationPrincipal User user
    ){
            try{
                UsersEntity usersEntity = registrationService.getUser(user);
                FractionEntity fraction = usersEntity.getUserFraction().getFraction();

                boolean result = fractionService.outFromFraction(fraction, usersEntity);

                if (result)return ResponseEntity.ok().build();
                else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            }catch (NullPointerException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFractionsUsers(
            @AuthenticationPrincipal User user,
            @PathVariable int id,
            @RequestParam(required = false) boolean sortByUsername,
            @RequestParam(required = false) boolean sortByLevel,
            @RequestParam(required = false) boolean sortByState
    ){
        FractionEntity fractionEntity = fractionService.getFraction(id);

        UsersEntity usersEntity = registrationService.getUser(user);
        Stream<UserFractionEntity> stream = fractionService.getListUsersInFraction(fractionEntity, usersEntity).stream();

        stream = (sortByUsername) ? stream.sorted(Comparator.comparing(userFractionEntity -> userFractionEntity.getUser().getLogin())) : stream;
        stream = (sortByLevel) ? stream.sorted(Comparator.comparing(userFractionEntity -> userFractionEntity.getUser().getLevel())) : stream;
        stream = (sortByState) ? stream.sorted(Comparator.comparing(userFractionEntity -> userFractionEntity.getStateUserFraction().getName())): stream;


        return ResponseEntity.ok(
            stream.collect(Collectors.toList())
        );
    }


    @Secured("ROLE_LEADER")
    @RequestMapping(value = "/{id}/accept", method = RequestMethod.POST)
    public ResponseEntity acceptUser(
            @RequestBody UserFractionEntity userFractionEntity,
            @PathVariable int id
    ){
        FractionEntity fractionEntity = fractionService.getFraction(id);
        try {
            userFractionEntity.setStateUserFraction(stateUserFractionRepository.findStateUserFractionEntityByName("player"));
            fractionService.updateUser(userFractionEntity);
            return ResponseEntity.ok(userFractionEntity);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Secured("ROLE_LEADER")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public ResponseEntity deleteUser(
            @RequestBody UserFractionEntity userFractionEntity,
            @PathVariable int id
    ){
        FractionEntity fractionEntity = fractionService.getFraction(id);
        try {
            userFractionRepository.delete(userFractionEntity);
            return ResponseEntity.ok().build();
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
}
