package com.spaceRangers.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceRangers.entities.BaseEntity;
import com.spaceRangers.entities.ResultsEntity;
import com.spaceRangers.entities.ShipEntity;
import com.spaceRangers.entities.UsersEntity;
import com.spaceRangers.impl.FilterService;
import com.spaceRangers.service.GameService;
import com.spaceRangers.service.ProfileUserService;
import com.spaceRangers.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    FilterService filterService;

    @Autowired
    ProfileUserService profileUserService;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    GameService gameService;

    @Secured("ROLE_USER")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(
            @AuthenticationPrincipal User user
    ){
        try {
            UsersEntity usersEntity = registrationService.getUser(user);

            if (filterService.isThisUser(usersEntity, user)) return ResponseEntity.ok(usersEntity);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "/base", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUsersBase(
            @AuthenticationPrincipal User user
    ){

        try {
            UsersEntity usersEntity = registrationService.getUser(user);

            return ResponseEntity.ok(usersEntity.getBases());


        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/base", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUsersBase(
            @AuthenticationPrincipal User user,
            @RequestBody String base
    ){
        try {
            UsersEntity usersEntity = registrationService.getUser(user);
            BaseEntity baseEntity = new ObjectMapper().readValue(base, BaseEntity.class);

            baseEntity.setUser(usersEntity);
            gameService.createBase(baseEntity);
            return ResponseEntity.ok(baseEntity);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/base/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBase(
            @PathVariable int id,
            @AuthenticationPrincipal User user
    ){

        try {
            UsersEntity usersEntity = registrationService.getUser(user);

            BaseEntity baseEntity = gameService.getUserBase(usersEntity, id);
            if (baseEntity == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            return ResponseEntity.ok(baseEntity);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/base/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateBase(
            @PathVariable int id,
            @RequestBody String base,
            @AuthenticationPrincipal User user
    ){

        try {
            UsersEntity usersEntity = registrationService.getUser(user);

            BaseEntity baseEntity = new ObjectMapper().readValue(base, BaseEntity.class);
            baseEntity.setId(id);

                if(filterService.usersBase(usersEntity, baseEntity)) {
                    baseEntity.setUser(usersEntity);
                    gameService.updateBase(baseEntity);
                    return ResponseEntity.ok(baseEntity);
                }

                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/ships", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getShips(
            @AuthenticationPrincipal User user
    ){
        try{
            return ResponseEntity.ok(registrationService.getUser(user).getShips());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/ships", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createShip(
            @AuthenticationPrincipal User user,
            @RequestBody String ship
    ){
        try {
            UsersEntity usersEntity = registrationService.getUser(user);
            ShipEntity shipEntity = new ObjectMapper().readValue(ship, ShipEntity.class);

            shipEntity.setUser(usersEntity);
            gameService.createShip(shipEntity);
            return ResponseEntity.ok(shipEntity);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/ships/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getShip(
            @PathVariable int id,
            @AuthenticationPrincipal User user
    ){
        try{

            UsersEntity usersEntity = registrationService.getUser(user);
            ShipEntity shipEntity = gameService.getShip(usersEntity, id);

            return (shipEntity == null) ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(shipEntity);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/ships/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateShip(
            @AuthenticationPrincipal User user,
            @RequestBody String ship,
            @PathVariable int id
    ){
        try {
            UsersEntity usersEntity = registrationService.getUser(user);
            ShipEntity shipEntity = new ObjectMapper().readValue(ship, ShipEntity.class);
            shipEntity.setId(id);
            filterService.isUsersShip(usersEntity, shipEntity);

            shipEntity.setUser(usersEntity);
            gameService.updateShip(shipEntity);
            return ResponseEntity.ok(shipEntity);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
