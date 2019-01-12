package com.spaceRangers.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceRangers.entities.*;
import com.spaceRangers.impl.FilterService;
import com.spaceRangers.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
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

    @ApiOperation("Get list of fractions")
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

    @ApiOperation("Create fraction by user")
    @Secured("ROLE_USER")
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createFracion(
            @ApiIgnore  @AuthenticationPrincipal User user,
            @ApiParam("Fraction entity") @RequestBody FractionEntity fraction
            ){
            FractionEntity fractionEntity = fractionService.createFraction(fraction, user);

            return ResponseEntity.ok(fractionEntity);
    }


    @ApiOperation("Get fraction by id")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFraction(
            @ApiParam("fraction id") @PathVariable int id
    ){
            return ResponseEntity.ok(fractionService.getFraction(id));
    }

    @ApiOperation("Get fraction's tasks")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFractionTasks(
            @ApiParam("fraction id") @PathVariable int id,
            @ApiIgnore @AuthenticationPrincipal User user,
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



    @ApiOperation("Create task for fraction")
    @Secured({
            "ROLE_LEADER",
            "ROLE_ADVISER"
    })
    @RequestMapping(value = "/{id}/tasks", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTask(
            @ApiIgnore @AuthenticationPrincipal User user,
            @ApiParam("fraction id") @PathVariable int id,
            @ApiParam("task entity") @RequestBody TaskEntity task
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


    @ApiOperation("Get fraction task by id task")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/tasks/{idTask}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTaskById(
            @ApiIgnore @AuthenticationPrincipal User user,
            @ApiParam("fraction id") @PathVariable int id,
            @ApiParam("task id") @PathVariable int idTask
    ){
            FractionEntity fraction = fractionService.getFraction(id);

            TaskEntity taskEntity = fractionService.getFractionTask(idTask);

            if(filterService.taskFilter(fraction, taskEntity, user)) return ResponseEntity.ok(taskEntity);
            else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ApiOperation("Update task states")
    @Secured("ROLE_LEADER")
    @RequestMapping(value = "/{id}/tasks/{idTask}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateTask(
            @ApiIgnore @AuthenticationPrincipal User user,
            @ApiParam("fraction id") @PathVariable int id,
            @ApiParam("task id") @PathVariable int idTask,
            @ApiParam("Task entity") @RequestBody TaskEntity task
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

    @ApiOperation("To join the fraction")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/join", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity subscribeUser(
        @ApiIgnore @AuthenticationPrincipal User user,
        @ApiParam("fraction id") @PathVariable int id
    ){
            FractionEntity fraction = fractionService.getFraction(id);
                UsersEntity usersEntity = registrationService.getUser(user);
            if (filterService.userInFraction(fraction, user))
                return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();

            try {
                UserFractionEntity userFraction = fractionService.joinFraction(fraction, usersEntity);

                if(userFraction == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                return ResponseEntity.ok(userFraction);

            }catch (NullPointerException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }

    @ApiOperation("To leave the fraction")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/out", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity outFromFraction(
            @ApiIgnore @AuthenticationPrincipal User user,
            @ApiParam("fraction id") @PathVariable int id
    ){
            FractionEntity fraction = fractionService.getFraction(id);

            try{
                UsersEntity usersEntity = registrationService.getUser(user);

                if(filterService.userInFraction(fraction, user)){
                    UserFractionEntity userFractionEntity = fractionService.outFromFraction(fraction, usersEntity);
                    if(userFractionEntity!=null) return ResponseEntity.ok(userFractionEntity);
                }

                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            }catch (NullPointerException e){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
    }

    @ApiOperation("Get fractions users")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFractionsUsers(
            @ApiIgnore @AuthenticationPrincipal User user,
            @PathVariable int id,
            @RequestParam(required = false) boolean sortByUsername,
            @RequestParam(required = false) boolean sortByLevel,
            @RequestParam(required = false) boolean sortByState
    ){
        FractionEntity fractionEntity = fractionService.getFraction(id);

        Stream<UserFractionEntity> stream = fractionService.getListUsersInFraction(fractionEntity, user).stream();

        stream = (sortByUsername) ? stream.sorted(Comparator.comparing(userFractionEntity -> userFractionEntity.getUser().getLogin())) : stream;
        stream = (sortByLevel) ? stream.sorted(Comparator.comparing(userFractionEntity -> userFractionEntity.getUser().getLevel())) : stream;
        stream = (sortByState) ? stream.sorted(Comparator.comparing(userFractionEntity -> userFractionEntity.getStateUserFraction().getName())): stream;


        return ResponseEntity.ok(
            stream.collect(Collectors.toList())
        );
    }

    @ApiOperation("Accept user")
    @Secured("ROLE_LEADER")
    @RequestMapping(value = "/{id}/accept", method = RequestMethod.POST)
    public ResponseEntity acceptUser(
            @RequestBody UserFractionEntity userFractionEntity,
            @AuthenticationPrincipal User user,
            @PathVariable int id
    ){
        FractionEntity fractionEntity = fractionService.getFraction(id);

        try {
            fractionService.updateUser(fractionEntity, userFractionEntity, user);
            return ResponseEntity.ok().build();
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }

    }
}
