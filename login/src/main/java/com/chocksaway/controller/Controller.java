package com.chocksaway.controller;

import com.chocksaway.domain.User;
import com.chocksaway.repository.UserRepository;
import com.chocksaway.exception.UserAlreadyExistsException;
import com.chocksaway.exception.UserNotFoundException;
import com.chocksaway.exception.UserUnauthorisedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    UserRepository repository;

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody User userIn) {
        Optional<User> user = Optional.ofNullable(repository.findByUsername(userIn.getUsername()));

        if (!user.isPresent()) {
            throw new UserNotFoundException(userIn.getUsername());
        }

        if (user.get().getPassword().equals(userIn.getPassword())) {
                return user.get().toString();
        }

        throw new UserUnauthorisedException(userIn.getUsername());
    }


    @PostMapping("/user")
    public ResponseEntity<?> add(@RequestBody User userIn) {

        if (userIn.getUsername() != null && userIn.getPassword() != null) {
            Optional<User> user = Optional.ofNullable(repository.findByUsername(userIn.getUsername()));

            if (!user.isPresent()) {
                repository.save(userIn);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }

        throw new UserAlreadyExistsException(userIn.getUsername());
    }


    @GetMapping("/user/{name}")
    public User retrieveStudent(@PathVariable String name) throws UserNotFoundException {
        Optional<User> user = Optional.ofNullable(repository.findByUsername(name));

        if (!user.isPresent())
            throw new UserNotFoundException(name);

        return user.get();
    }

}
