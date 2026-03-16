package com.example.demo.db1.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db1.entity.User;
@Repository
public interface UserDAO extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);

}