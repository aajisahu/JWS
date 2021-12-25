package com.springbootrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * this class is used for
 * Define one PasswordEncoder and use before saveUser into DB
 *
 * Modify code in IUserServiceImpl for usersave(), encode password and save.
 *
 */
@Configuration
public class AppConfig {

    @Bean
    public BCryptPasswordEncoder encoder(){
        return  new BCryptPasswordEncoder();
    }



}
