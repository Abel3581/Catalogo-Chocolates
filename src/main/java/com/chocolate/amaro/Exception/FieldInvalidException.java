package com.chocolate.amaro.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FieldInvalidException extends RuntimeException{
    public FieldInvalidException(final String message) {
        super(message);}
}
