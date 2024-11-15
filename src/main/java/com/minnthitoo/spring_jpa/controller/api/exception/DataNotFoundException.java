package com.minnthitoo.spring_jpa.controller.api.exception;

public class DataNotFoundException extends Exception{
    public DataNotFoundException(String message){
        super(message);
    }
}
