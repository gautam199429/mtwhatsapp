package com.moneytree.moneywhatsapp.exceptions;

import java.util.List;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.moneytree.moneywhatsapp.responsebody.APIResponse;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionResolverClass {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return new ResponseEntity<>(new APIResponse(e.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleAccessDeniedException(Exception e) {
        return new ResponseEntity<>(new APIResponse(e.getMessage()), HttpStatus.OK);
    }


    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<Object> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
        return new ResponseEntity<>(new APIResponse(e.getMessage()), HttpStatus.OK);
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNotFoundResourceException(NoHandlerFoundException e) {
        return new ResponseEntity<>(new APIResponse("Requested Resource Not Found"), HttpStatus.OK);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        return new ResponseEntity<>(new APIResponse("Request method not allowed for this resource"), HttpStatus.OK);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<Object> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
        return new ResponseEntity<>(new APIResponse(ex.getHeaderName()+" is required"), HttpStatus.OK);
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleHttpMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return new ResponseEntity<>(new APIResponse(ex.getParameterName()+" is required"), HttpStatus.OK);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<Object> handleHttpMissingParametersException(MissingPathVariableException ex) {
        return new ResponseEntity<>(new APIResponse(ex.getVariableName()+" is required"), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleHttpMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(new APIResponse(ex.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return new ResponseEntity<>(new APIResponse(errors.get(0)), HttpStatus.OK);
    }

}
