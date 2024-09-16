package org.example.schoolsystem.infra;

import org.example.schoolsystem.exception.BadRequestException;
import org.example.schoolsystem.exception.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(BadRequestException.class)
    public String handleBadRequestException(BadRequestException e){
        return e.getMessage();
    }

}
