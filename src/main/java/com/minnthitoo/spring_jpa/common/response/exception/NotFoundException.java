package com.minnthitoo.spring_jpa.common.response.exception;

import lombok.Data;

import java.util.Map;

@Data
public class NotFoundException extends Exception{
    Map<String, String> errors;
    public NotFoundException(String message, Map<String, String> errors){
        super(message);
        this.errors = errors;
    }
}
