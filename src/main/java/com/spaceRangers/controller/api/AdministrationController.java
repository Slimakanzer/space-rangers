package com.spaceRangers.controller.api;

import com.spaceRangers.entities.UsersEntity;
import com.spaceRangers.service.AdministrationService;
import com.spaceRangers.service.ProfileUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admin")
public class AdministrationController {

    @Autowired
    @Qualifier(value = "administrationService")
    private AdministrationService administrationService;

    @Autowired
    @Qualifier("profileUserService")
    private ProfileUserService profileUserService;


    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    @ResponseBody
    ResponseEntity dropUser( @RequestBody UsersEntity user) {
        try {

            return ResponseEntity.ok(
                    administrationService.dropUser(user)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    @ResponseBody
    ResponseEntity updateUser( @RequestBody UsersEntity user){
        try{

            return ResponseEntity.ok(
                    administrationService.updateUser(user)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @RequestMapping(value = "/user/state", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity getStateUserByUserId( @RequestParam("id_user")int id_user){
        try{

            return ResponseEntity.ok(
                    administrationService.getStateUserByIdUser(id_user)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
