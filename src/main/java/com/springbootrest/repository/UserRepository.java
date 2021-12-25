package com.springbootrest.repository;

import com.springbootrest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository <User, Integer> {
}
