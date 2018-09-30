package com.chocksaway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Author milesd on 16/09/2018.
 */

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserUnauthorisedException extends RuntimeException {
    public UserUnauthorisedException() {}

    public UserUnauthorisedException(String message)
    {
        super(message);
    }

}
