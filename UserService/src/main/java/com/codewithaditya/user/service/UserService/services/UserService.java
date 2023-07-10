package com.codewithaditya.user.service.UserService.services;

import com.codewithaditya.user.service.UserService.entities.User;

import java.util.List;

public interface UserService {

    //create
    User createUser(User user);

    //read
    User getUser(String userId);

    //read all
    List<User> getAllUser();


    //update
}
