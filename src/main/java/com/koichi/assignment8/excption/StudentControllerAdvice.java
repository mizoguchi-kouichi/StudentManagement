package com.koichi.assignment8.excption;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class StudentControllerAdvice {

    @ExceptionHandler(value = StudentNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(
            StudentNotFoundException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MultipleMethodsException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(
            MultipleMethodsException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.BAD_REQUEST.value()),
                "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<Map<String, String>> errors = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            Map<String, String> error = new HashMap<>();
            error.put("field", fieldError.getField());
            error.put("message", fieldError.getDefaultMessage());
            errors.add(error);
        });

        ErrorResponse errorResponse =
                new ErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), "validation error", ZonedDateTime.now().toString(), errors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    /**
     * エラーレスポンスのクラス
     */
    public static final class ErrorResponse {
        private final String status;
        private final String message;
        private final String timestamp;
        private final List<Map<String, String>> errors;

        public ErrorResponse(String status, String message, String timestamp, List<Map<String, String>> errors) {
            this.status = status;
            this.message = message;
            this.timestamp = timestamp;
            this.errors = errors;
        }

        public String getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public List<Map<String, String>> getErrors() {
            return errors;
        }
    }

}



