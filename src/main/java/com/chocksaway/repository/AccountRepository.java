package com.chocksaway.repository;

import com.chocksaway.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {

    public Account findByUsername(String username);

}