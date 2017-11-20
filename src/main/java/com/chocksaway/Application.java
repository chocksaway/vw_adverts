package com.chocksaway;

import com.chocksaway.entity.Account;
import com.chocksaway.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    CommandLineRunner init(final AccountRepository accountRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... arg0) throws Exception {
                accountRepository.save(new Account("username", "password"));
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
