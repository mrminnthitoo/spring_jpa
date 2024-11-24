package com.minnthitoo.spring_jpa.common.response.exception;

import lombok.Data;
import org.springframework.validation.FieldError;

import java.util.List;

@Data
public class BeanValidationException extends Exception{

    private List<FieldError> errors;

    public BeanValidationException(String message, List<FieldError> errors){
        super(message);
        this.errors = errors;
    }
}
