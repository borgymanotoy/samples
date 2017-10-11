package com.sample.rest.demo.springbootrest.services;

public interface SecurityService {

    String findLoggedInUsername();
    void autologin(String username, String password);

}
