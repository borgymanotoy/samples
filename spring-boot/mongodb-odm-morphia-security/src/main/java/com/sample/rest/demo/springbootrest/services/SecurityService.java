package com.sample.rest.demo.springbootrest.services;

public interface SecurityService {

    String findLoggedInUsername();
    boolean autologin(String username, String password);

}
