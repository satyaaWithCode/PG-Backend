package com.springBoot.MyrPg.handler;

import com.springBoot.MyrPg.DTO.BuilderResponseDTO;
import com.springBoot.MyrPg.exception.DuplicateUserException;
import com.springBoot.MyrPg.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<BuilderResponseDTO<Object>> handleStudentNotFound(StudentNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<BuilderResponseDTO<Object>> handleUsernameNotFound(UsernameNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }


    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<BuilderResponseDTO<Object>> handleDuplicateUser(DuplicateUserException ex) {
        return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage()); // 409 Conflict
    }


    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<BuilderResponseDTO<Object>> handleFileSize(MaxUploadSizeExceededException ex) {
        return buildErrorResponse(HttpStatus.PAYLOAD_TOO_LARGE, "File too large!");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BuilderResponseDTO<Object>> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");
        return buildErrorResponse(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BuilderResponseDTO<Object>> handleGeneric(Exception ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong: " + ex.getMessage());
    }

    private ResponseEntity<BuilderResponseDTO<Object>> buildErrorResponse(HttpStatus status, String message) {
        BuilderResponseDTO<Object> response = BuilderResponseDTO.<Object>builder()
                .timestamp(LocalDateTime.now().toString())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .data(null)
                .build();

        return ResponseEntity.status(status).body(response);
    }
}
