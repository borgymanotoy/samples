package com.samples.rest.springboot.models;

public class User {

    private Integer id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String contactNo;


    public User(){}
    public User(String username, String firstname, String lastname, String email){
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
    public User(String username, String firstname, String lastname, String email, String contactNo){
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.contactNo = contactNo;
    }
    public User(Integer id, String username, String firstname, String lastname, String email, String contactNo){
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.contactNo = contactNo;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
}