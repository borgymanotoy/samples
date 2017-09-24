package com.sample.rest.demo.springbootrest.models;

import org.hibernate.validator.constraints.Email;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

@Entity(value = "users", noClassnameStored = true)
public class User {

/*
    {
        "username": "borgymanotoy",
        "firstName": "Borgy",
        "lastName": "Manotoy",
        "email": "borgymanotoy@pogi.ph",
        "contactNo": "(0949) 993-0422",
        "password": "letmein123"
    }
*/

    @Id
    private String username;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String contactNo;
    private String password;
    private Date lastModifiedDate;

    public User(){}
    public User(String username, String firstName, String lastName, String email, String contactNo, String password){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNo = contactNo;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public boolean validate(){
        if(null!=username && null!=firstName && null!=lastName)
            return true;
        else
            return false;
    }
}
