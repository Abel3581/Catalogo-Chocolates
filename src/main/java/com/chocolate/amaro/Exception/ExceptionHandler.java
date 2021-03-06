package com.chocolate.amaro.Exception;

import com.chocolate.amaro.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(
            HttpServletRequest request,
            EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(buildResponse(e, HttpStatus.NOT_FOUND));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(
            HttpServletRequest request,
            AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(buildResponse(e, HttpStatus.UNAUTHORIZED));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(
            HttpServletRequest request,
            InvalidCredentialsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildResponse(e, HttpStatus.BAD_REQUEST));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(
            HttpServletRequest request,
            UsernameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildResponse(e, HttpStatus.BAD_REQUEST));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(
            HttpServletRequest request,
            BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(buildResponse(e, HttpStatus.UNAUTHORIZED));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            HttpServletRequest request,
            Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildResponse(e, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(OperationNotAllowedException.class)
    public ResponseEntity<ErrorResponse> handleOperationNotAllowedException(
            HttpServletRequest request,
            OperationNotAllowedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(buildResponse(e, HttpStatus.FORBIDDEN));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(
            HttpServletRequest request,
            UserAlreadyExistException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildResponse(e, HttpStatus.BAD_REQUEST));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ThirdPartyException.class)
    public ResponseEntity<ErrorResponse> handleThirdPartyException(
            HttpServletRequest request,
            ThirdPartyException e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(buildResponse(e, HttpStatus.SERVICE_UNAVAILABLE));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<ErrorResponse> handleInvalidArgumentException(
            HttpServletRequest request,
            InvalidArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildResponse(e, HttpStatus.BAD_REQUEST));
    }

    private ErrorResponse buildResponse(Exception e, HttpStatus httpStatus) {
        return new ErrorResponse(e, httpStatus.value());
    }

    private ErrorResponse buildResponse(String message, HttpStatus httpStatus) {
        return new ErrorResponse(message, httpStatus.value());
    }
}
