package com.spaceRangers.controller.api;


import com.spaceRangers.entities.UserAccountEntity;
import com.spaceRangers.entities.UsersEntity;
import com.spaceRangers.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Qualifier(value = "registrationService")
    @Autowired
    RegistrationService registrationService;


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity registerUser(@RequestBody UserAccountEntity userAccountEntity){
        UsersEntity user = registrationService.createUser(userAccountEntity.getLogin(), userAccountEntity.getPassword());
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity loginUser(@RequestBody UserAccountEntity userAccountEntity){
        UsersEntity user = registrationService.loginUser(userAccountEntity.getLogin(), userAccountEntity.getPassword());
        return ResponseEntity.ok(user);
    }
}
