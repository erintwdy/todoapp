package com.example.todoapp.exception;

import com.example.todoapp.model.dto.response.ApiResponse;
import com.example.todoapp.exception.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ VALIDATION ERROR (MULTIPLE)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleValidation(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return new ApiResponse(400, "Validation failed", errors, true);
    }

    // ✅ NOT FOUND (FIXED)
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse handleNotFound(NotFoundException ex) {
        return new ApiResponse(404, ex.getMessage(), null, true);
    }

    // ✅ BAD REQUEST (ENUM / LOGIC ERROR)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleBadRequest(IllegalArgumentException ex) {
        return new ApiResponse(400, "Input tidak valid", null, true);
    }

    // ✅ GENERAL ERROR (AMAN)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleAll(Exception ex) {
        return new ApiResponse(500, "Terjadi kesalahan pada server", null, true);
    }
}