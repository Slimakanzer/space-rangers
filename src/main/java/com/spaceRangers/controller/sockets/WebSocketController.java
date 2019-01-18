package com.spaceRangers.controller.sockets;

import com.spaceRangers.entities.BaseEntity;
import com.spaceRangers.entities.ShipEntity;
import com.spaceRangers.entities.StateShipEntity;
import com.spaceRangers.entities.UsersEntity;
import com.spaceRangers.repository.ShipRepository;
import com.spaceRangers.repository.StateShipRepository;
import com.spaceRangers.repository.UserRepository;
import com.spaceRangers.service.GameService;
import com.spaceRangers.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
public class WebSocketController {

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameService gameService;

    @Autowired
    StateShipRepository stateShipRepository;


    @MessageMapping("/ship/move")
    @SendTo("/topic/move")
    public ShipEntity move(
            @Payload ShipEntity ship
    ){

        System.out.println("MOVE MESS");
        return shipRepository.save(ship);
    }


    @MessageMapping("/ship/attack")
    @SendTo("/topic/attack")
    public ShipEntity attac(
            @Payload ShipEntity ship
    ){

        System.out.println("ATTACk MESS");

        if (ship.getHp() <= 0){
            ship.setHp(0);
            ship.setStateShip(stateShipRepository.findById(1).get());
        }

        return shipRepository.save(ship);
    }

    @MessageMapping("/ship/delete")
    @SendTo("/topic/delete")
    public ShipEntity delete(
            @Payload ShipEntity ship
    ){
        System.out.println("DELETE MESS");
        return ship;
    }

    @MessageMapping("/user/update")
    @SendTo("/topic/user")
    public boolean updateUserAccount(
            @Payload Boolean bool
    ){
        System.out.println("UPDATE MESS");
        return true;
    }


    @MessageMapping("/base/create")
    @SendTo("/topic/base")
    public BaseEntity createBase(
            @Payload BaseEntity base
    ){
        System.out.println("CREATE BASE MESS");
        return base;
    }

    @MessageMapping("/fraction/update")
    @SendTo("/topic/fraction")
    public int updateUserFraction(
            @Payload int id
    ){
        System.out.println("UPDATE USER FRACTION MESS");
        return id;
    }


    @MessageMapping("/ship/boost")
    @SendTo("/topic/game")
    public ShipEntity boostShip(
            @Payload ShipEntity ship,
            Principal principal
    ) throws Exception {
        UsersEntity usersEntity = registrationService
                .getUserAccount(principal.getName())
                .getUser();
        System.out.println(usersEntity.getId());

        System.out.println("BOOST MESS");

        ship.setUser(usersEntity);
        return gameService.powerupShip(ship);
    }

    @MessageMapping("/ship/create")
    @SendTo("/topic/create")
    public ShipEntity createShip(
            @Payload ShipEntity ship,
            Principal principal
    ) throws Exception {
        System.out.println("CREATE MESS");

        UsersEntity usersEntity = registrationService
                .getUserAccount(principal.getName())
                .getUser();

        ship.setUser(usersEntity);
        return gameService.createShip(ship);

    }

    @MessageExceptionHandler
    @SendToUser("/topic/error")
    public Exception handleException(Exception exception){
        System.out.println("ERROR MESS"+ exception.getMessage());
        return exception;
    }


}
