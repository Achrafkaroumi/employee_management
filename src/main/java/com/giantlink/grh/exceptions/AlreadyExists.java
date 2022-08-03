package com.giantlink.grh.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AlreadyExists extends Exception {
    public AlreadyExists(String message) {
        super(message);
    }
}
