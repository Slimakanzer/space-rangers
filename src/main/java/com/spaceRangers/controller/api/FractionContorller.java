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
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity getAllFractions(){
        try{
            return ResponseEntity.ok(fractionService.getListFractions());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation("Create fraction by user")
    @Secured("ROLE_USER")
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createFracion(
            @ApiIgnore  @AuthenticationPrincipal User user,
            @ApiParam("Fraction entity") @RequestBody FractionEntity fraction
            ){
        try{
            FractionEntity fractionEntity = fractionService.createFraction(fraction, user);

            return ResponseEntity.ok(fractionEntity);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @ApiOperation("Get fraction by id")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFraction(
            @ApiParam("fraction id") @PathVariable int id
    ){
        try{
            return ResponseEntity.ok(fractionService.getFraction(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation("Get fraction's tasks")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFractionTasks(
            @ApiParam("fraction id") @PathVariable int id,
            @ApiIgnore @AuthenticationPrincipal User user
    ){
        try{

            FractionEntity fraction = fractionService.getFraction(id);

            List<TaskEntity> tasks = fraction.getTasks()
                    .stream()
                    .filter(e->filterService.taskFilter(fraction, e, user))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(tasks);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
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
        try{
            task.setId(null);

            try{
                FractionEntity fraction = fractionService.getFraction(id);

                task.setFraction(fraction);
                advisersPlayerFractionService.createTask(task);
                return ResponseEntity.ok(task);

            }catch (NullPointerException e){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }


        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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
        try{
            FractionEntity fraction = fractionService.getFraction(id);

            TaskEntity taskEntity = fractionService.getFractionTask(idTask);

            if(filterService.taskFilter(fraction, taskEntity, user)) return ResponseEntity.ok(taskEntity);
            else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
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


        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation("To join the fraction")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/join", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity subscribeUser(
        @ApiIgnore @AuthenticationPrincipal User user,
        @ApiParam("fraction id") @PathVariable int id
    ){
        try {
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
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation("To leave the fraction")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/out", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity outFromFraction(
            @ApiIgnore @AuthenticationPrincipal User user,
            @ApiParam("fraction id") @PathVariable int id
    ){

        try{
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
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
