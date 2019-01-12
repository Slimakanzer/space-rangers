package com.spaceRangers.controller.api;


import com.spaceRangers.entities.UserAccountEntity;
import com.spaceRangers.entities.UsersEntity;
import com.spaceRangers.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class RegistrationController {

    @Qualifier(value = "registrationService")
    @Autowired
    RegistrationService registrationService;


    @RequestMapping(value = "/registration", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity registration(@RequestParam String username, @RequestParam String password){
        try {
            registrationService.registration(username, password);
            return ResponseEntity.ok().build();
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
