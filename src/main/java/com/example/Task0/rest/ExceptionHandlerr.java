package com.example.Task0.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ExceptionHandlerr {

    @ExceptionHandler
    public ResponseEntity<TaskManagerErrorResponse> handle(IdNotFoundError e){
        TaskManagerErrorResponse temp=new TaskManagerErrorResponse(HttpStatus.NOT_FOUND.value(),e.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(temp,HttpStatus.NOT_FOUND);
    }
   @ExceptionHandler
    public ResponseEntity<TaskManagerErrorResponse> handle(Exception e){
        TaskManagerErrorResponse temp=new TaskManagerErrorResponse(HttpStatus.BAD_REQUEST.value(),e.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(temp,HttpStatus.BAD_REQUEST);
    }






}
