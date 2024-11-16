package com.cerberus.identityservice.controller;

import com.cerberus.identityservice.exception.UserValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(UserValidationException.class)
    public ProblemDetail handleException(UserValidationException exception){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, exception.getMessage()
        );
        problemDetail.setTitle("Ошибка валидации пользователя");
        return problemDetail;
    }
}
