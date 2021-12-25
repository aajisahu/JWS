package com.springbootrest.model;

import lombok.Data;


/**
 * http Request json data
 */
@Data
public class UserRequest {

    private String username;
    private String password;



}
