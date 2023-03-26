package com.example.BackEnd.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.BackEnd.repository.UserRepository;
import com.example.BackEnd.model.User;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/{name}")
    public ResponseEntity<User> loadOneByName(@PathVariable String name) {
        LOGGER.info("start loadOne user by name: ", name);
        try {
            User user = userRepository.findByUsername(name).orElse(null);
            LOGGER.info("Found: {}", user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public String loadAllUsername() {
        LOGGER.info("start all username: ");
        try {
            List<User> user = userRepository.findAll();
            LOGGER.info("Found: {}", user);
            return user.stream().map(User::getUsername).collect(Collectors.joining(","));
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return e.getMessage();
        }
    }

    
}