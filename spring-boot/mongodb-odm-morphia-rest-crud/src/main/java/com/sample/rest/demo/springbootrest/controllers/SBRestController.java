package com.sample.rest.demo.springbootrest.controllers;

import com.sample.rest.demo.springbootrest.models.MessageState;
import com.sample.rest.demo.springbootrest.models.User;
import com.sample.rest.demo.springbootrest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = {"http://localhost:8000", "http://localhost:9000"})
public class SBRestController {

    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> greetings() {
        String greetings = "Hello Davao City, Philippines";
        return new ResponseEntity<Object>(greetings, HttpStatus.OK);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody User user) {
        MessageState msgState = new MessageState("ERROR", "Unable to add user!");
        if(null!=user){
            user.setLastModifiedDate(new Date());
            this.userRepository.saveOrUpdateUser(user);
            msgState.setCode("OK");
            msgState.setMessage("Successefully added user!");
        }

        return new ResponseEntity<>(msgState, HttpStatus.OK);
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@RequestParam(value = "username") String username){
        User user = this.userRepository.getUser(username);
        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestBody User user){
        MessageState msgState = new MessageState("ERROR", "Unable to update user!");
        User dbUser = this.userRepository.getUser(user.getUsername());
        if(null!=dbUser){
            dbUser.setFirstName(user.getFirstName());
            dbUser.setLastName(user.getLastName());
            dbUser.setEmail(user.getEmail());
            dbUser.setContactNo(user.getContactNo());
            dbUser.setPassword(user.getPassword());
            dbUser.setLastModifiedDate(new Date());

            this.userRepository.saveOrUpdateUser(dbUser);

            msgState.setCode("OK");
            msgState.setMessage("Successfully updated user!");
        }

        return new ResponseEntity<>(msgState, HttpStatus.OK);
    }

    @RequestMapping(value = "/removeUser", method = RequestMethod.POST)
    public ResponseEntity<?> removeUser(@RequestParam(value = "username") String username){
        MessageState msgState = new MessageState("ERROR", "Unable to remove user!");
        User user = this.userRepository.getUser(username);
        if(null!=user){
            this.userRepository.deleteUser(user);
            msgState.setCode("OK");
            msgState.setMessage("Successfully removed user!");
        }

        return new ResponseEntity<>(msgState, HttpStatus.OK);
    }

    @RequestMapping(value = "/listUsers", method = RequestMethod.GET)
    public ResponseEntity<?> listUsers(){
        List<User> users = this.userRepository.listUsers(null);
        return new ResponseEntity<Object>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchUsers", method = RequestMethod.GET)
    public ResponseEntity<?> searchUsers(@RequestParam(value = "searchKey") String searchKey){
        List<User> users = this.userRepository.listUsers(searchKey);
        return new ResponseEntity<Object>(users, HttpStatus.OK);
    }
}