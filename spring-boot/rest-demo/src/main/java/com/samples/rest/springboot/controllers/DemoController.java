package com.samples.rest.springboot.controllers;

import com.samples.rest.springboot.models.User;
import com.samples.rest.springboot.models.Users;
import com.samples.rest.springboot.utilities.ObjectToJsonString;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = {"http://localhost:8000", "http://localhost:9000"})
public class DemoController {

    private Users users = new Users();


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> greetings() {
        String greetings = "Hello Davao City, Philippines";
        return new ResponseEntity<Object>(greetings, HttpStatus.OK);
    }

    @RequestMapping(value = "/greet", method = RequestMethod.GET)
    public ResponseEntity<?> greetings(@RequestParam(value = "name", required = false) String name) {
        if(null==name || 0 == name.trim().length()) name = "Davao City, Philippines";
        String greetings = "Hello " + name + "!";
        return new ResponseEntity<Object>(greetings, HttpStatus.OK);
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@RequestParam(value = "userId", defaultValue = "-1") int userId) {
        User user = users.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public ResponseEntity<?> newUser() {
        User user = new User();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody User user) throws NotFound{
        boolean status;

        if(null == user)
            throw new NotFound();
        else
            status = users.addUser(user);

        if(status)
            return new ResponseEntity<>("User added successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Problem adding user.", HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestBody User user) throws NotFound{
        boolean status;
        if(null == user)
            throw new NotFound();
        else
            status = users.updateUser(user.getId(), user);

        if(status)
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Problem updating user.", HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(value = "/removeUser", method = RequestMethod.POST)
    public ResponseEntity<?> removeUser(@RequestParam(value = "userId") int userId) {
        if(users.removeUser(userId))
            return new ResponseEntity<>("User removed successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Problem removing user.", HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(value = "/listUsers", method = RequestMethod.GET)
    public ResponseEntity<?> listUsers(@RequestParam(value = "callback", required = false) String callback) {
        Set<User> listUsers = users.listUsers();
        String responseText = ObjectToJsonString.serializeToJson(listUsers, callback);
        return new ResponseEntity<>(responseText, HttpStatus.OK);
    }


}