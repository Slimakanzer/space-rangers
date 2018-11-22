package com.spaceRangers.controller.api;

import com.spaceRangers.entities.UsersEntity;
import com.spaceRangers.service.AdministrationService;
import com.spaceRangers.service.ProfileUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "/api/admin")
public class AdministrationController {

    @Autowired
    @Qualifier(value = "administrationService")
    private AdministrationService administrationService;

    @Autowired
    @Qualifier("profileUserService")
    private ProfileUserService profileUserService;

    @ApiOperation("Delete user from application")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully deleted user"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    @ResponseBody
    ResponseEntity dropUser(@ApiParam("Users entity") @RequestBody UsersEntity user) {
        try {

            return ResponseEntity.ok(
                    administrationService.dropUser(user)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation("Update users state in application")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully updated users state"),
            @ApiResponse(code = 404, message = "User not found")
    }
    )
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    @ResponseBody
    ResponseEntity updateUser(@ApiParam("Users entity") @RequestBody UsersEntity user){
        try{

            return ResponseEntity.ok(
                    administrationService.updateUser(user)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation("Get users state by id user")
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "Successfully got users state"),
                    @ApiResponse(code = 404, message = "User not found")
            }
    )
    @RequestMapping(value = "/user/state", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity getStateUserByUserId(@ApiParam(name = "Users id", defaultValue = "1") @RequestParam("id_user")int id_user){
        try{

            return ResponseEntity.ok(
                    administrationService.getStateUserByIdUser(id_user)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
