package com.chocksaway.repository;

import com.chocksaway.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Author milesd on 16/09/2018.
 */

@Component
public class UserDataLoader implements CommandLineRunner {
    @Autowired
    UserRepository repository;

    private final Logger logger = LoggerFactory.getLogger(UserDataLoader.class);

    @Override
    public void run(String... strings) throws Exception {
        logger.info("Loading user data...");
        repository.save(new User("miles", "password"));
        repository.save(new User("john", "password"));
        repository.save(new User("james", "password"));
    }
}
