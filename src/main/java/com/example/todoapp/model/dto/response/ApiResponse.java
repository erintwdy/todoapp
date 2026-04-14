package com.example.todoapp.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

    private int statusCode;
    private String message;
    private T data;
    private Object errors;

    // SUCCESS
    public ApiResponse(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.errors = null;
    }

    // ERROR
    public ApiResponse(int statusCode, String message, Object errors, boolean isError) {
        this.statusCode = statusCode;
        this.message = message;
        this.errors = errors;
        this.data = null;
    }
}