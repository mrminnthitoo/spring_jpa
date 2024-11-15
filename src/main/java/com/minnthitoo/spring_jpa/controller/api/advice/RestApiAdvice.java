package com.minnthitoo.spring_jpa.controller.api.advice;

import com.minnthitoo.spring_jpa.controller.api.dto.ApiError;
import com.minnthitoo.spring_jpa.controller.api.dto.ErrorCodes;
import com.minnthitoo.spring_jpa.controller.api.exception.BeanValidationException;
import com.minnthitoo.spring_jpa.controller.api.exception.DataNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class RestApiAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request){
        log.info("handleMethodArgumentTypeMismatch");
        String error = ex.getName() + " should be type of " + ex.getRequiredType().getName();
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_PARAMETER_TYPE.toString(), ex.getLocalizedMessage(), error
        );
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus()
        );
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(DataNotFoundException exception){
        log.info("handleEntityNotFoundException");
        String error = exception.getMessage();
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                ErrorCodes.ENTITY_NOT_FOUND.toString(),
                exception.getMessage(),
                error
        );
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus()
        );
    }

    @ExceptionHandler(BeanValidationException.class)
    public ResponseEntity<Object> handleBeanValidationException(BeanValidationException exception){
        log.info("handleBeanValidation");
        List<String> errors = new ArrayList<>();
        for (ObjectError error : exception.getErrors()){
            errors.add(error.getDefaultMessage());
        }
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ErrorCodes.BEAN_VALIDATION_ERROR.toString(),
                exception.getMessage(),
                errors
        );
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus()
        );
    }

}
