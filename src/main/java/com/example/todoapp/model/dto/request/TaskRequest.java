package com.example.todoapp.model.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {

    @NotBlank(message = "Title tidak boleh kosong")
    @Size(max = 100, message = "Title maksimal 100 karakter")
    private String title;

    @Size(max = 255, message = "Description maksimal 255 karakter")
    private String description;

    @NotNull(message = "Due date wajib diisi")
    private LocalDate dueDate;

    @NotBlank(message = "Priority wajib diisi")
    private String priority;

    @NotBlank(message = "Status wajib diisi")
    private String status;

    @NotNull(message = "User wajib diisi")
    private Long userId;

    @NotNull(message = "Category wajib diisi")
    private Long categoryId;
}