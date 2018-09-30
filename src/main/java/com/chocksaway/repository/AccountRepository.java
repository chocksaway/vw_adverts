package com.chocksaway.repository;

import com.chocksaway.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
    //@Query("{ 'username' : {$regex: ?0, $options: 'i' }}")
    Account findByUsername(final String username);
}