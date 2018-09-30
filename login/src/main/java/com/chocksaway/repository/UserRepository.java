package com.chocksaway.repository;

import com.chocksaway.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Author milesd on 15/09/2018.
 */


public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);


}