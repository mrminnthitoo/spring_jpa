package com.minnthitoo.spring_jpa.common.response.dto;

import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
public class ApiError {
    private HttpStatusCode status;
    private String errorCode;
    private String message;
    private List<Map<String, String>> errors;

    public ApiError(HttpStatusCode status, String errorCode, String message, List<Map<String, String>> errors){
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatusCode status, String errorCode, String message, Map<String, String> error){
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
        this.errors = Collections.singletonList(error);
    }
}
