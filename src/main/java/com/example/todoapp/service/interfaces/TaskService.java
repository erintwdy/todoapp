package com.example.todoapp.service.interfaces;

import com.example.todoapp.model.dto.request.TaskRequestDTO;
import com.example.todoapp.model.dto.response.TaskResponseDTO;

import java.util.List;

public interface TaskService {

    List<TaskResponseDTO> getAll();

    TaskResponseDTO create(TaskRequestDTO request);

    TaskResponseDTO getById(Long id);

    TaskResponseDTO update(Long id, TaskRequestDTO request);

    void delete(Long id);
}