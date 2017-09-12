package com.samples.rest.springboot.models;

import java.util.HashSet;
import java.util.Set;

public class Users {

    private Set<User> users_set = new HashSet<>();

    public Users(){
        populateUsers();
    }


    private void populateUsers(){
        users_set.clear();

        users_set.add(new User(101, "akomykel", "Michael Angelo", "Mahinay", "akomykel@gmail.com", "(0925) 123-2345"));
        users_set.add(new User(102, "john_baricuatro", "Jonh Goldeto", "Baricuatro", "john.baricuatro@johngoldgroup.com", "(918) 888-8888"));
        users_set.add(new User(103, "jay_canangga", "Joseph Jones", "Canangga", "jay.canangga@johngoldgroup.com", "(0923) 234-4622"));
        users_set.add(new User(104, "bobalite", "Bryan", "Balite", "bryan.balite@johngoldgroup.com", null));
        users_set.add(new User(105, "anton_delara","Anthony", "De Lara", "anton.delara@johngoldgroup.com", null));
        users_set.add(new User(106, "eric_ahmad", "Eric Jose", "Salip Ahmad", "eric.ahmad@johngoldgroup.com", "(919) 321-7654"));
        users_set.add(new User(107, "joy_guerra", "Bernadette Joy", "Guerra", "joy.guerra@johngoldgroup.com", null));
        users_set.add(new User(108, "liza_cajipe", "Liza Claire", "Cajipe", "liza.cajipe@johngoldgroup.com", null));
        users_set.add(new User(109, "rea_dela_cruz", "Rhea", "Dela Cruz", "rea.delacruz@johngoldgroup.com", "(949) 135-2366"));
        users_set.add(new User(110, "cristine_magatao", "Cristine", "Magatao", "cristine.magatao@johngoldgroup.com", "(912) 998-3482"));
        users_set.add(new User(111, "rose_gabaya", "Rosaly", "Gabaya", "rose.gabaya@johngoldgroup.com", "(920) 249-2499"));
        users_set.add(new User(112, "casper_ghost", "Casper", "Multo", "casper_d_friendly_ghost@gmail.com", null));
    }

    public boolean addUser(User user) {
        if(null!=user){
            users_set.add(user);
            return true;
        }
        return false;
    }

    public boolean removeUser(int userId){

        System.out.println("[REMOVE-USER-ID]: " + userId);

        if(0 < userId){
            for(User u : users_set){
                if(userId == u.getId()) {
                    users_set.remove(u);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean updateUser(int userId, User user){
        if(0 < userId){
            return removeUser(userId) && addUser(user);
        }
        return false;
    }

    public User getUser(int userId){
        if(0 < userId){
            for(User u : users_set){
                if(userId == u.getId()) return u;
            }
        }
        return null;
    }

    public Set<User> listUsers(){
        return users_set;
    }

}
