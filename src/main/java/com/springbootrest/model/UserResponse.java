package com.springbootrest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Http Response JSON Data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String token;
    private String message;
}
