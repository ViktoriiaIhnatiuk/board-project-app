package com.example.boardprojectapp.service.impl;

import com.example.boardprojectapp.model.Task;
import com.example.boardprojectapp.repository.TaskRepository;
import com.example.boardprojectapp.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
   private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find task by id: " + id));
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
