package com.chocksaway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Author milesd on 16/09/2018.
 */

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {}

    public UserAlreadyExistsException(String message)
    {
        super(message);
    }

}
