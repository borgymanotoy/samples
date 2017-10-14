package com.sample.rest.demo.springbootrest;

import com.sample.rest.demo.springbootrest.models.Role;
import com.sample.rest.demo.springbootrest.models.User;
import com.sample.rest.demo.springbootrest.services.RoleService;
import com.sample.rest.demo.springbootrest.services.SecurityService;
import com.sample.rest.demo.springbootrest.services.UserService;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AutoLoginTest extends SpringBootRestApplicationTests {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;



    @Before
    public void initializeTest(){
        List<Role> roles;

        if(null==this.roleService.list() || 0 == this.roleService.list().size()){
            roleService.save(new Role("ADMIN"));
            roleService.save(new Role("USER"));
            roleService.save(new Role("ACTUATOR"));
        }

        roles = this.roleService.list();

        if(null==this.userService.list(null) || 0 == this.userService.list(null).size()){
            User user = new User("dummyUser", "test");
            user.setRoles(roles);

            System.out.println("[USER]: " + user.toString());
            System.out.println("[VALID]: " + user.validate());

            this.userService.save(user);
        }
    }

    @Test
    public void test01AutoLogin(){
        String username = "dummyUser";
        String password = "test";

        boolean success = securityService.autologin(username, password);
        System.out.println("[login-success]: " + success);

        TestCase.assertTrue(success);
    }

    @After
    public void cleanUp(){
        this.userService.remove("dummyUser");
    }
}
