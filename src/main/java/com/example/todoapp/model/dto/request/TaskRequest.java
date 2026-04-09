package com.example.todoapp.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class TaskRequest {

    private String title;
    private String description;
    private String priority;
    private String status;
    private LocalDate dueDate;
    private Long userId;
    private Long categoryId;
}