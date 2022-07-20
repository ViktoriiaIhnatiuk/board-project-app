package com.example.boardprojectapp.service;

import com.example.boardprojectapp.model.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);

    List<Task> findAll();

    Task findById(Long id);

    void delete(Long id);
}
