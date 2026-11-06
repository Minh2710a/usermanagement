package com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.http.HttpStatus;

import com.example.demo.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(AppException.class)
        public ResponseEntity<ErrorResponse> handleAppException(
                        AppException exception) {

                ErrorCode errorCode = exception.getErrorCode();

                ErrorResponse response = new ErrorResponse(
                                errorCode.getCode(),
                                errorCode.getMessage());

                return ResponseEntity
                                .status(errorCode.getStatusCode())
                                .body(response);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleException(
                        Exception exception) {

                ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;

                ErrorResponse response = new ErrorResponse(
                                errorCode.getCode(),
                                errorCode.getMessage());

                return ResponseEntity
                                .status(errorCode.getStatusCode())
                                .body(response);
        }

        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<ErrorResponse> handleAccessDenied(
                        AccessDeniedException exception) {

                return ResponseEntity
                                .status(HttpStatus.FORBIDDEN)
                                .body(new ErrorResponse(
                                                403,
                                                "Access denied"));
        }
}