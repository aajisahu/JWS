package com.springbootrest.controller;

import com.springbootrest.model.User;
import com.springbootrest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService; //Has a relation

    //1. Save User Data In Database
    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody User user){

        Integer id= userService.saveUser(user);
        String body= "User "+id+" Saved";
        //return new ResponseEntity<String>(body, HttpStatus.OK);
        return ResponseEntity.ok(body);


    }

    // 2. Validate User and Generate Token(login)







}
