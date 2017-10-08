package com.sample.rest.demo.springbootrest.controllers;

import com.google.gson.Gson;
import com.sample.rest.demo.springbootrest.models.Contact;
import com.sample.rest.demo.springbootrest.models.MessageState;
import com.sample.rest.demo.springbootrest.repositories.ContactRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = {"http://localhost:8000", "http://localhost:9000", "https://borgymanotoy.auth0.com"})
public class SBRestController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping(value = "/")
    public ResponseEntity<?> greetings() {
        String greetings = "Hello Davao City, Philippines";
        return new ResponseEntity<Object>(greetings, HttpStatus.OK);
    }


    @GetMapping(value = "/listContact")
    public ResponseEntity<?> listContact(){
        List<Contact> contacts = this.contactRepository.listContact();
        return new ResponseEntity<Object>(contacts, HttpStatus.OK);
    }

    @PostMapping(value = "/addContact")
    public ResponseEntity<?> addContact(@RequestBody Contact contact) {
        MessageState msgState = new MessageState("ERROR", "Unable to add contact!");
        if(null!= contact){
            contact.setLastModifiedDate(new Date());
            String id = this.contactRepository.saveOrUpdateContact(contact);
            msgState.setCode("OK");
            msgState.setMessage("Successefully added contact!");
            if(null!=id) msgState.setResultObjectId(id);
        }

        return new ResponseEntity<>(msgState, HttpStatus.OK);
    }

    @GetMapping(value = "/getContact")
    public ResponseEntity<?> getContact(@RequestParam(value = "id") ObjectId id){
        Contact contact = this.contactRepository.getContactById(id);
        return new ResponseEntity<Object>(contact, HttpStatus.OK);
    }

    @PostMapping(value = "/updateContact")
    public ResponseEntity<?> updateContact(@RequestBody Contact contact){
        Gson gson = new Gson();
        System.out.println("\n[update-contact]: " + gson.toJson(contact));


        MessageState msgState = new MessageState("ERROR", "Unable to update contact!");
        if(null!= contact){
            String id = this.contactRepository.updateContact(contact);
            msgState.setCode("OK");
            msgState.setMessage("Successfully updated contact!");
            if(null!=id) msgState.setResultObjectId(id);
        }

        return new ResponseEntity<>(msgState, HttpStatus.OK);
    }

    @PostMapping(value = "/removeContact")
    public ResponseEntity<?> removeContact(@RequestParam(value = "id") ObjectId id){
        MessageState msgState = new MessageState("ERROR", "Unable to remove contact!");
        Contact dbContact = this.contactRepository.getContactById(id);
        if(null!= dbContact){
            this.contactRepository.deleteContact(dbContact);
            msgState.setCode("OK");
            msgState.setMessage("Successfully removed contact!");
        }

        return new ResponseEntity<>(msgState, HttpStatus.OK);
    }
}