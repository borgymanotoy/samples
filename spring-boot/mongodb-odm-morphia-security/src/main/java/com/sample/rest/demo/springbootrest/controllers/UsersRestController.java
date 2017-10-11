package com.sample.rest.demo.springbootrest.controllers;

import com.sample.rest.demo.springbootrest.models.MessageState;
import com.sample.rest.demo.springbootrest.models.User;
import com.sample.rest.demo.springbootrest.services.RoleService;
import com.sample.rest.demo.springbootrest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = {"http://localhost:8000", "http://localhost:9000", "https://borgymanotoy.auth0.com"})
public class UsersRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping(value = "/users")
    public ResponseEntity<?> listUsers(@RequestParam(value = "search", required = false) String search){
        List<User> users = this.userService.list(search);
        return new ResponseEntity<Object>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<?> getUser(@RequestParam(value = "un") String username){
        User user = this.userService.findByUsername(username);
        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<?> registerUser(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "verifyPassword") String verifyPassword,
            @RequestParam(value = "roles") String[] roles){
        MessageState msgState = new MessageState();
        boolean success = false;
        if(0 < username.trim().length() && 0 < password.trim().length() && 0 < roles.length && 0 < verifyPassword.trim().length()){
            if(verifyPassword.equalsIgnoreCase(password)){
                User user = new User(username);
                user.setPassword(password);
                user.setRoles(this.roleService.getRoleList(roles));

                if(this.userService.save(user)){
                    msgState.setCode("OK");
                    msgState.setMessage("Registration Successful.");
                    success = true;
                }
                else {
                    msgState.setCode("ERROR");
                    msgState.setMessage("Registration failed, user already exists!");
                }
            }
            else {
                msgState.setCode("ERROR");
                msgState.setMessage("Passwords does not match!");
            }
        }
        else {
            msgState.setCode("ERROR");
            msgState.setMessage("Please fill-up all the registration fields.");
        }

        if(success)
            return new ResponseEntity<Object>(msgState, HttpStatus.OK);
        else
            return new ResponseEntity<Object>(msgState, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "/user")
    public ResponseEntity<?> removeUser(@RequestParam(value = "un") String username){
        boolean success = this.userService.remove(username);
        if(success)
            return new ResponseEntity<Object>("Successfully removed user(" + username + ").", HttpStatus.OK);
        else
            return new ResponseEntity<Object>("Unable to remove user.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
