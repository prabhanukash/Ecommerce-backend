package com.flipkart.ecommerce_backend.hander;

import com.flipkart.ecommerce_backend.Exception.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle all BusinessExceptions (UserException, EmailException, TokenException)
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        log.warn("Business exception occurred: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = ErrorResponse.of(
                ex.getErrorCode(),
                ex.getMessage(),
                ex.getDetails()
        );

        return new ResponseEntity<>(errorResponse, ex.getErrorCode().getHttpStatus());
    }

    /**
     * Handle validation errors from @Valid annotation
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.of(
                ErrorCode.VALIDATION_ERROR,
                "Validation failed",
                details
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle invalid credentials
     */
    @ExceptionHandler(InvalidUserCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(InvalidUserCredentialsException ex) {
        log.warn("Invalid credentials exception occurred: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = ErrorResponse.of(
                ex.getErrorCode(),
                ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handle user not verified
     */
    @ExceptionHandler(UserNotVerifiedException.class)
    public ResponseEntity<ErrorResponse> handleUserNotVerified(UserNotVerifiedException ex) {
        log.warn("User not verified exception occurred: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = ErrorResponse.of(
                ex.getErrorCode(),
                ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    /**
     * Handle general exceptions not caught by other handlers
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.error("Unexpected error occurred: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = ErrorResponse.of(
                ErrorCode.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred. Please try again later."
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle HTTP method not supported
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(
            HttpRequestMethodNotSupportedException ex) {

        ErrorResponse errorResponse = ErrorResponse.of(
                ErrorCode.BAD_REQUEST,
                String.format("HTTP method '%s' not supported", ex.getMethod())
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseException(DatabaseException ex) {
        log.error("Database error occurred: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = ErrorResponse.of(
                ex.getErrorCode(),
                ex.getMessage(),
                ex.getDetails()
        );

        return new ResponseEntity<>(errorResponse, ex.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        log.error("Data integrity violation: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = ErrorResponse.of(
                ErrorCode.DATABASE_CONSTRAINT_VIOLATION,
                "Database constraint violation occurred",
                Collections.singletonList(ex.getMostSpecificCause().getMessage())
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenVerificationException.class)
    public ResponseEntity<ErrorResponse> handleTokenVerificationException(TokenVerificationException ex) {
        log.error("Token verification failed: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = ErrorResponse.of(
                ex.getErrorCode(),
                ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, ex.getErrorCode().getHttpStatus());
    }
}
