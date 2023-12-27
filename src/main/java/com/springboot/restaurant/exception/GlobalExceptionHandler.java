package com.springboot.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { BadRequestException.class })
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), ex.getStatusCode());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { UnauthorizedException.class })
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), ex.getStatusCode());
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    // Add more exception handlers for other exceptions as needed

    // Define a common error message class
    private static class ErrorMessage {
        private final String message;
        private final int statusCode;

        public ErrorMessage(String message, int statusCode) {
            this.message = message;
            this.statusCode = statusCode;
        }

        public String getMessage() {
            return message;
        }

        public int getStatusCode() {
            return statusCode;
        }
    }
}

