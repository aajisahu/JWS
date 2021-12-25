package com.springbootrest.service;

import com.springbootrest.model.User;
import com.springbootrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IUserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository; //HAS a relation

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    //Save method
    @Override
    public Integer saveUser(User user) {
        user.setPassword(
                bCryptPasswordEncoder.encode(
                        user.getPassword())
        );// return the encoded password
        return userRepository.save(user).getId();
    }

    //Get User By Username
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        //Model Class User
        Optional<User> opt= findByUsername(username);
            if (!opt.isPresent()){
                throw  new UsernameNotFoundException("User Not Exist!!");
            }
        //Read User From Database
        User user= opt.get();
        //Spring Security User Object
        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                user.getRoles().stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList())
        );
    }
}
