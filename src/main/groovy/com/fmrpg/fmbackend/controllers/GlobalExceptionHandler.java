package com.fmrpg.fmbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//TODO: Implement global exception handling for the application

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
}
