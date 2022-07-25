package com.example.plannerapp.service;

import com.example.plannerapp.model.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);

    List<Task> findAll();

    Task findById(Long id);

    Task delete(Long id);
}
