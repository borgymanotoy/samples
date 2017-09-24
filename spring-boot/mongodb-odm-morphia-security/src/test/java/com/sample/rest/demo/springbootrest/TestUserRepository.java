package com.sample.rest.demo.springbootrest;

import com.sample.rest.demo.springbootrest.models.User;
import com.sample.rest.demo.springbootrest.repositories.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class TestUserRepository extends SpringBootRestApplicationTests {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testListUsers(){
        List<User> listUsers = this.userRepository.listUsers();
        assertNotNull(listUsers);
    }

    @Test
    public void testSaveOrUpdateUser(){
        User user = new User();
        user.setUsername("dummyUser");
        user.setFirstName("Dummy");
        user.setLastName("User 01");
        user.setLastModifiedDate(new Date());

        assertTrue(user.validate());

        this.userRepository.saveOrUpdateUser(user);
    }

    @Test
    @SuppressWarnings("unused")
    public void testGetUser(){
        String username = "dummyUser";
        assertNotNull(username);

        User user = this.userRepository.getUser(username);
        assertNotNull(user);
    }

    @Test
    public void testRemoveUser(){
        User dbUser = this.userRepository.getUser("dummyUser");
        assertNotNull(dbUser);

        this.userRepository.deleteUser(dbUser);
    }
}
