package com.sample.rest.demo.springbootrest.repositories;

import com.mongodb.WriteResult;
import com.sample.rest.demo.springbootrest.models.User;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private Datastore datastore;

    @Override
    public Key<User> saveOrUpdateUser(User user) {
        if(null!=user && user.validate())
            return datastore.save(user);
        else
            return null;
    }

    @Override
    public User getUser(String username) {
        return datastore.get(User.class, username);
    }

    @Override
    public UpdateResults updateUser(User user, UpdateOperations<User> operations) {
        if(null!=user && user.validate())
            return datastore.update(user, operations);
        else
            return null;
    }

    @Override
    public WriteResult deleteUser(User user) {
        return datastore.delete(user);
    }

    @Override
    public UpdateOperations<User> createOperations() {
        return datastore.createUpdateOperations(User.class);
    }

    @Override
    public List<User> listUsers() {
        return datastore.find(User.class).order("username").asList();
    }

}
