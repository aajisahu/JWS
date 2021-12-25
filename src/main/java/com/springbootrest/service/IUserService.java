package com.springbootrest.service;

import com.springbootrest.model.User;

import java.util.Optional;

public interface IUserService {

    //Encode Password
    Integer saveUser(User user);

    Optional<User> findByUsername(String username);


}
