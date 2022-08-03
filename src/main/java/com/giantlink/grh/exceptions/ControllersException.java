package com.giantlink.grh.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllersException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyExists.class)
    public ResponseEntity<Object> alreadyExistsException(AlreadyExists alreadyExistsException) {
        Map<String, Object> message = new HashMap<>();
        message.put("Message", alreadyExistsException.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleAlreadyExistsException(NotFoundException notFoundException) {
        Map<String, Object> message = new HashMap<>();
        message.put("Message", notFoundException.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status , WebRequest request) {
        Map<String, Object> Message = new HashMap<>();
        Message.put("Message", ex.getFieldError().getDefaultMessage());
        return new ResponseEntity<>(Message, HttpStatus.BAD_REQUEST);
    }



}
