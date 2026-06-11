package com.hidrobo.course.restaurant_review_app.controllers;

import java.util.List;
import java.util.Locale;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.hidrobo.course.restaurant_review_app.domain.dtos.ApiErrorResponse;
import com.hidrobo.course.restaurant_review_app.domain.dtos.ApiErrorResponse.FieldError;
import com.hidrobo.course.restaurant_review_app.exceptions.BaseException;
import com.hidrobo.course.restaurant_review_app.exceptions.StorageException;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ErrorController {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<?> handleArgumentNotValidException(MethodArgumentNotValidException ex, Locale locale) {
                log.error("Caught MethodArgumentNotValidException: ", ex);

                List<FieldError> errors = ex.getFieldErrors().stream().map(err -> FieldError.builder()
                                .field(err.getField())
                                .error(err.getDefaultMessage())
                                .build()).toList();

                ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                                .status(HttpStatus.BAD_REQUEST.value())
                                .message("One or more fields have errors. Please correct them and try again.")
                                .errors(errors)
                                .build();

                log.info(ex.getMessage());

                return new ResponseEntity<>(
                                errorResponse,
                                HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(InternalServerError.class)
        public ResponseEntity<ApiErrorResponse> handleInternalServerError(InternalServerError ex) {
                log.error("Caught InternalServerError: ", ex);
                ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .message(ex.getMessage())
                                .build();

                log.info(ex.getMessage());

                return new ResponseEntity<ApiErrorResponse>(
                                errorResponse,
                                HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(BaseException.class)
        public ResponseEntity<ApiErrorResponse> handleBaseException(BaseException ex) {
                log.error("Caught BaseException: ", ex);
                ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .message(ex.getMessage())
                                .build();
                return new ResponseEntity<ApiErrorResponse>(
                                errorResponse,
                                HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
                log.error("Caught unexpected exception: ", ex);

                ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .message(ex.getMessage())
                                .build();
                return new ResponseEntity<ApiErrorResponse>(
                                errorResponse,
                                HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(StorageException.class)
        public ResponseEntity<ApiErrorResponse> handleStorageException(StorageException ex) {
                log.error("Caught StorageException: ", ex);
                ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .message(ex.getMessage())
                                .build();
                return new ResponseEntity<ApiErrorResponse>(
                                errorResponse,
                                HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<ApiErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
                log.error("Caught EntityNotFoundException: ", ex);
                ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .message(ex.getMessage())
                                .build();
                return new ResponseEntity<ApiErrorResponse>(
                                errorResponse,
                                HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(BadCredentialsException.class)
        public ResponseEntity<ApiErrorResponse> badCredentialsException(BadCredentialsException ex) {
                ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                                .status(HttpStatus.UNAUTHORIZED.value())
                                .message("Incorrect username or password")
                                .build();
                return new ResponseEntity<ApiErrorResponse>(
                                errorResponse,
                                HttpStatus.UNAUTHORIZED);
        }

        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolationException(
                        DataIntegrityViolationException ex) {
                log.error("Caught DataIntegrityViolationException: ", ex);
                String message = "";

                if (ex.getMessage().contains("unique_day_of_week_restaurant")) {
                        message = "A restaurant cannot have more than 1 operating hour per day";
                }
                if (ex.getMessage().contains("users_username_key")) {
                        message = "Username already exists";
                }
                if (ex.getMessage().contains("users_email_key")) {
                        message = "Email already exists";
                } 

                if (ex.getMessage().contains("reviews_user_id_restaurant_id_key")) {
                        message = "A user cannot review the same restaurant more than once";
                }

                else {
                        message = "Error en la consulta";
                }

                ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                                .status(HttpStatus.BAD_REQUEST.value())
                                .message(message)
                                .build();

                log.info(message);

                return new ResponseEntity<ApiErrorResponse>(
                                errorResponse,
                                HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<ApiErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
                ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                                .status(HttpStatus.FORBIDDEN.value())
                                .message(ex.getMessage())
                                .build();
                return new ResponseEntity<ApiErrorResponse>(
                                errorResponse,
                                HttpStatus.FORBIDDEN);
        }


}
