package com.example.Quiz.API.EceptionHandler;

import com.example.Quiz.Quick_Pojo_Class.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.postgresql.util.PSQLException;
import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;

@ControllerAdvice
public class APIExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    ErrorMessage exceptionHandler(ValidationException e){
        return new ErrorMessage("400",e.getMessage());
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    ErrorMessage exceptionHandler(HttpMessageNotReadableException e){
        return new ErrorMessage("400",e.getLocalizedMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    ErrorMessage exceptionHandler(EntityNotFoundException e){
        return new ErrorMessage("404",e.getMessage());
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler
    ErrorMessage exceptionHandler(BadCredentialsException e){
        return new ErrorMessage("401",e.getMessage());
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    ErrorMessage exceptionHandler(UsernameNotFoundException e){
       return new ErrorMessage("404",e.getMessage());
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    ErrorMessage exceptionHandler(PSQLException e){
        return new ErrorMessage("400",e.getMessage());
    }
}
