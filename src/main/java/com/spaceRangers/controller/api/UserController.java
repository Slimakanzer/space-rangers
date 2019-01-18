package com.spaceRangers.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceRangers.entities.*;
import com.spaceRangers.impl.FilterService;
import com.spaceRangers.repository.ShipRepository;
import com.spaceRangers.repository.UserRepository;
import com.spaceRangers.service.BattleService;
import com.spaceRangers.service.GameService;
import com.spaceRangers.service.ProfileUserService;
import com.spaceRangers.service.RegistrationService;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Autowired
    BattleService battleService;

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    UserRepository userRepository;



    @Secured("ROLE_USER")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(
            @AuthenticationPrincipal User user
    ){
            UsersEntity usersEntity = registrationService.getUser(user);

            if (filterService.isThisUser(usersEntity, user)) return ResponseEntity.ok(usersEntity);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateInfoUser(
            @AuthenticationPrincipal User user,
            @RequestBody UsersEntity usersEntityUpdate
    ){
        try {
            UsersEntity usersEntity = registrationService.getUser(user);

            usersEntity.setFirstName(usersEntityUpdate.getFirstName());
            usersEntity.setLastName(usersEntityUpdate.getLastName());
            usersEntity.setLogin(usersEntityUpdate.getLogin());

            UsersEntity result = userRepository.save(usersEntity);

            return ResponseEntity.ok(result);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }



    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "/base", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUsersBase(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) boolean sortByName,
            @RequestParam(required = false) boolean sortBySystem,
            @RequestParam(required = false) boolean sortByCountShips
    ){
            UsersEntity usersEntity = registrationService.getUser(user);

            Stream<BaseEntity> stream = usersEntity.getBases().stream();

            stream = (sortByName) ? stream.sorted(Comparator.comparing(BaseEntity::getNameBase)) : stream;
            stream = (sortBySystem) ? stream.sorted(Comparator.comparing(baseEntity -> baseEntity.getSystem().getNameSystem())) : stream;
            stream = (sortByCountShips) ? stream.sorted(Comparator.comparing(baseEntity -> baseEntity.getShips().size())) : stream;


            return ResponseEntity.ok(stream.collect(Collectors.toList()));
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/fraction", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUsersFraction(
            @AuthenticationPrincipal User user
    ){
        UsersEntity usersEntity = registrationService.getUser(user);
        return ResponseEntity.ok(usersEntity.getUserFraction());
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/fraction", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean canAttack(
            @AuthenticationPrincipal User user,
            @RequestBody ShipEntity shipEntity
    ){
        UsersEntity usersEntity = registrationService.getUser(user);

        if(usersEntity.getUserFraction() == null) return false;

        ShipEntity shipEntity1 = shipRepository.findById(shipEntity.getId()).get();

        if (shipEntity1.getUser().getUserFraction() == null) return false;

        if (shipEntity1.getUser().getUserFraction().getStateUserFraction().getName().equals("candidate")) return false;
        if (usersEntity.getUserFraction().getStateUserFraction().getName().equals("candidate")) return false;

        return usersEntity.getUserFraction().getFraction().equals(shipEntity1.getUser().getUserFraction().getFraction());

    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/base", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUsersBase(
            @AuthenticationPrincipal User user,
            @RequestBody BaseEntity baseEntity
    ){
        try {
            UsersEntity usersEntity = registrationService.getUser(user);

            baseEntity.setUser(usersEntity);
            gameService.createBase(baseEntity);

            System.out.println("CREATE BASE CONTROLLER");

            return ResponseEntity.ok(baseEntity);
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/base/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBase(
            @PathVariable int id,
            @AuthenticationPrincipal User user
    ){
            UsersEntity usersEntity = registrationService.getUser(user);

            BaseEntity baseEntity = gameService.getUserBase(usersEntity, id);
            if (baseEntity == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            return ResponseEntity.ok(baseEntity);
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/base/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateBase(
            @PathVariable int id,
            @RequestBody BaseEntity baseEntity,
            @AuthenticationPrincipal User user
    ){

            UsersEntity usersEntity = registrationService.getUser(user);
            baseEntity.setId(id);

                if(filterService.usersBase(usersEntity, baseEntity)) {
                    baseEntity.setUser(usersEntity);
                    gameService.updateBase(baseEntity);
                    return ResponseEntity.ok(baseEntity);
                }

                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/base/{id}/ships", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBaseShips(
            @PathVariable int id,
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) boolean sortByType,
            @RequestParam(required = false) boolean sortByName
    ){
        UsersEntity usersEntity = registrationService.getUser(user);

        try {
                BaseEntity baseEntity = gameService.getUserBase(usersEntity, id);
                if (baseEntity == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            Stream<ShipEntity> stream = baseEntity.getShips().stream();

            stream = (sortByType) ? stream.sorted(Comparator.comparing(shipEntity -> shipEntity.getTypeShip().getName())) : stream;
            stream = (sortByName) ? stream.sorted(Comparator.comparing(ShipEntity::getNameShip)) : stream;

            return ResponseEntity.ok(stream.collect(Collectors.toList()));
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/ships", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getShips(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) boolean sortByType,
            @RequestParam(required = false) boolean sortByName,
            @RequestParam(required = false) boolean sortByHp,
            @RequestParam(required = false) boolean sortBySpeed,
            @RequestParam(required = false) boolean sortByProtection
    ){

            UsersEntity users = registrationService.getUser(user);
            Stream<ShipEntity> stream = users.getShips().stream();

            stream = (sortByType) ? stream.sorted(Comparator.comparing(shipEntity -> shipEntity.getTypeShip().getName())) : stream;
            stream = (sortByName) ? stream.sorted(Comparator.comparing(ShipEntity::getNameShip)) : stream;
            stream = (sortByHp) ? stream.sorted(Comparator.comparing(ShipEntity::getHp)) : stream;
            stream = (sortBySpeed) ? stream.sorted(Comparator.comparing(ShipEntity::getSpeed)) : stream;
            stream = (sortByProtection) ? stream.sorted(Comparator.comparing(ShipEntity::getProtection)) : stream;

            return ResponseEntity.ok(stream.collect(Collectors.toList()));

    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/ships", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createShip(
            @AuthenticationPrincipal User user,
            @RequestBody ShipEntity shipEntity
    ) throws Exception {

        try {
            UsersEntity usersEntity = registrationService.getUser(user);


            if(
                    usersEntity.getCoins() < shipEntity.getTypeShip().getCost()
            ) return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body("You don't have money");

            shipEntity.setUser(usersEntity);
            gameService.createShip(shipEntity);

            usersEntity.setCoins(usersEntity.getCoins()-shipEntity.getTypeShip().getCost());
            return ResponseEntity.ok(shipEntity);
        } catch (NoSuchFieldException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/ships/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getShip(
            @PathVariable int id,
            @AuthenticationPrincipal User user
    ){
            UsersEntity usersEntity = registrationService.getUser(user);
            ShipEntity shipEntity = gameService.getShip(usersEntity, id);

            return (shipEntity == null) ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(shipEntity);

    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/ships", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateShip(
            @AuthenticationPrincipal User user,
            @RequestBody ShipEntity shipEntity
    ){
            UsersEntity usersEntity = registrationService.getUser(user);
            if(filterService.isUsersShip(usersEntity, shipEntity)) {

                try{

                    shipEntity.setUser(usersEntity);
                    gameService.updateShip(shipEntity);
                    return ResponseEntity.ok(shipEntity);

                }catch (DataIntegrityViolationException e){
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new String("To many powerup"));
                }
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/battles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUsersBattle(
            @AuthenticationPrincipal User user
    ){
            try{
                UsersEntity usersEntity = registrationService.getUser(user);
                return ResponseEntity.ok(battleService.getBattlesUser(usersEntity));

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/battles/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBattle(
            @PathVariable int id,
            @AuthenticationPrincipal User user
    ){
            try{
                BattleEntity battleEntity = battleService.getBattle(id);
                if(filterService.isUsersBattle(user, battleEntity)) return ResponseEntity.ok(battleEntity);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/battles/{id}/ships", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getShipsInBattle(
            @PathVariable int id,
            @AuthenticationPrincipal User user
    ){
            try{
                BattleEntity battleEntity = battleService.getBattle(id);
                if(filterService.isUsersBattle(user, battleEntity)){
                    return ResponseEntity.ok(battleEntity.getShips());

                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/battles/{id}/ships", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addShipInBattle(
            @PathVariable int id,
            @RequestBody ShipEntity shipEntity,
            @AuthenticationPrincipal User user
    ){
            try{
                BattleEntity battleEntity = battleService.getBattle(id);
                if(filterService.isUsersBattle(user, battleEntity)){

                    battleEntity.getShips().add(shipEntity);
                    battleService.createBattle(battleEntity);
                    return ResponseEntity.ok(battleEntity);
                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }
}
