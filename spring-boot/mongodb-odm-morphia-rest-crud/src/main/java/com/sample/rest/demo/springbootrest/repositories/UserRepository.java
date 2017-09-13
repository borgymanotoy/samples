package com.sample.rest.demo.springbootrest.repositories;

import com.mongodb.WriteResult;
import com.sample.rest.demo.springbootrest.models.User;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import java.util.List;

public interface UserRepository {

    Key<User> saveOrUpdateUser(User user);
    User getUser(String username);
    UpdateResults updateUser(User user, UpdateOperations<User> operations);
    WriteResult deleteUser(User user);
    UpdateOperations<User> createOperations();
    List<User> listUsers(String searchKey);

}
