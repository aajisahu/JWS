package com.springbootrest.service;

import com.springbootrest.model.User;
import com.springbootrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IUserServiceImpl implements IUserService{

    @Autowired
    private UserRepository userRepository; //HAS a relation

    @Override
    public Integer saveUser(User user) {


        return userRepository.save(user).getId();
    }
}
