package com.chocksaway.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Author milesd on 16/09/2018.
 */

@Component
public class AccountDataLoader implements CommandLineRunner {
    @Autowired
    AccountRepository repository;

    private final Logger logger = LoggerFactory.getLogger(AccountDataLoader.class);

    @Override
    public void run(String... strings) throws Exception {
        logger.info("Not Loading Account data...");
//        repository.save(new Account("username", "password"));
//        repository.save(new Account("miles", "password"));
//        repository.save(new Account("john", "password"));
//        repository.save(new Account("james", "password"));
    }
}
