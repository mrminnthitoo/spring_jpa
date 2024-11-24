package com.minnthitoo.spring_jpa.common.response;

import com.minnthitoo.spring_jpa.common.response.dto.ApiError;
import com.minnthitoo.spring_jpa.common.response.errorcode.ErrorCodes;
import com.minnthitoo.spring_jpa.common.response.exception.BeanValidationException;
import com.minnthitoo.spring_jpa.common.response.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
public class ErrorResponse extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception){
        String error = "Parameter type mismatch error.";
        Map<String, String> errors = new HashMap<>();
        errors.put(exception.getName(), exception.getName() + " should be type of " + Objects.requireNonNull(exception.getRequiredType()).getName());
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ErrorCodes.INVALID_PARAMETER_TYPE.toString(),
                error,
                errors

        );
        return new ResponseEntity<Object>(
                apiError,
                new HttpHeaders(),
                apiError.getStatus()
        );
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception){
        String error = exception.getMessage();
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                ErrorCodes.NOT_FOUND.toString(),
                exception.getLocalizedMessage(),
                exception.getErrors()
        );
        return new ResponseEntity<Object>(
                apiError,
                new HttpHeaders(),
                apiError.getStatus()
        );
    }

    @ExceptionHandler(value = BeanValidationException.class)
    public ResponseEntity<Object> handleBeanValidationException(BeanValidationException exception){
        String error = exception.getMessage();

        List<Map<String, String>> errors = new ArrayList<>();

        for (FieldError err : exception.getErrors()){
            Map<String, String> errorToAdd = new HashMap<>();
            errorToAdd.put(err.getField(), err.getDefaultMessage());
            errors.add(errorToAdd);
        }

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ErrorCodes.VALIDATION_ERROR.toString(),
                exception.getLocalizedMessage(),
                errors

        );
        return new ResponseEntity<Object>(
                apiError,
                new HttpHeaders(),
                apiError.getStatus()
        );
    }

}
