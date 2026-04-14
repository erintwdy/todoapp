package com.example.todoapp.service.interfaces;

import com.example.todoapp.model.dto.request.TaskRequest;
import com.example.todoapp.model.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {

    List<TaskResponse> getAll();

    TaskResponse create(TaskRequest request);
}