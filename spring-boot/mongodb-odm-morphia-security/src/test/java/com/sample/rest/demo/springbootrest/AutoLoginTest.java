package com.sample.rest.demo.springbootrest;

import com.sample.rest.demo.springbootrest.services.SecurityService;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AutoLoginTest extends SpringBootRestApplicationTests {

    @Autowired
    private SecurityService securityService;


    @Test
    public void test01AutoLogin(){
        String username = "lmsalipahmad";
        String password = "letmein123";

        boolean success = securityService.autologin(username, password);
        System.out.println("[login-success]: " + success);

        TestCase.assertTrue(success);
    }

}
