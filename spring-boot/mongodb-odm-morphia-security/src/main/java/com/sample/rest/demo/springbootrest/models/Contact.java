package com.sample.rest.demo.springbootrest.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sample.rest.demo.springbootrest.models.serializers.ObjectIdJsonSerializer;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Serialized;

import java.util.Date;
import java.util.List;

@Entity(value = "contacts", noClassnameStored = true)
public class Contact {

/*
    {
        "firstName": "Borgy",
        "lastName": "Manotoy",
        "email": "borgymanotoy@pogi.ph",
        "contactNo": "(0949) 993-0422",
        "password": "letmein123"
    }
*/

    @Id
    @JsonSerialize(using = ObjectIdJsonSerializer.class)
    private ObjectId id;
    private String firstName;
    private String lastName;

    @Email
    private String email;
    private String contactNo;
    private Date creationDate;
    private Date lastModifiedDate;

    private String owner;

    public Contact() {}
    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact(String firstName, String lastName, String email, String contactNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNo = contactNo;
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public boolean validate() {
        if (null != firstName && null != lastName)
            return true;
        else
            return false;
    }

}


