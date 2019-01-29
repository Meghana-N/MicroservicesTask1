package com.stackroute.contoller;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.exception.UserNotFoundEXception;
import com.stackroute.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("music")
@Api(value = "User data", description = "This is a user data implemented in REST")
public class UserController {
    //Trackservice variable
    private UserService userService;

    //Set TrackService variable using trackcontroller constructor
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    //Insert user into the database
    @ApiOperation(value = "Insert a User", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("user")
    public ResponseEntity<?> insertUser(@RequestBody User user) throws UserAlreadyExistsException {
        ResponseEntity responseEntity;
        User user21= userService.saveTheUser(user);
        responseEntity=new ResponseEntity<String>("Success-track stored", HttpStatus.CREATED);
        return responseEntity;
    }

    //GetAll users in the databases
    @ApiOperation(value = "Get All Users", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("user")
    public ResponseEntity<?> getAllUsers() throws UserNotFoundEXception {
        ResponseEntity responseEntity;
        responseEntity= new ResponseEntity<List<User>>(userService.returnAllUsers(),HttpStatus.ACCEPTED);
        return responseEntity;
    }

    //Update the password of the user
    @ApiOperation(value = "Update the password", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PutMapping("user")
    public ResponseEntity<?> updatePasswords(@RequestParam("userid") int userId,@RequestParam("password") String password) throws UserNotFoundEXception {
        ResponseEntity responseEntity;
        User user34= userService.updatePassword(userId,password);
        responseEntity= new ResponseEntity<String>("Succees-track updated",HttpStatus.OK);
        return responseEntity;
    }

    //Delete the user
    @ApiOperation(value = "Delete user", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping("user")
    public ResponseEntity<?> deleteUser(@RequestParam int userId) throws UserNotFoundEXception {
        List<User> sometrack= userService.deletUser(userId);
        ResponseEntity responseEntity= new ResponseEntity<String>("Success-Track deleted",HttpStatus.ACCEPTED);
        return responseEntity;
    }
}
