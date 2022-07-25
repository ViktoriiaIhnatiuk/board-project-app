package com.example.plannerapp.controller;

import com.example.plannerapp.dto.request.SectionRequestDto;
import com.example.plannerapp.dto.request.TaskRequestDto;
import com.example.plannerapp.dto.response.TaskResponseDto;
import com.example.plannerapp.service.TaskService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/sections/{id}")
    private TaskResponseDto createTask(@PathVariable Long id, @RequestBody TaskRequestDto taskRequestDto) {
        return taskService.createTask(id, taskRequestDto);
    }

    @GetMapping
    public List<TaskResponseDto> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponseDto getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public TaskResponseDto updateTaskById(@PathVariable Long id,
                                  @RequestBody TaskRequestDto taskRequestDto) {
        return taskService.updateTaskById(id, taskRequestDto);
    }

    @PutMapping("/{id}/section-update")
    public TaskResponseDto replaceTaskToOtherSection(@PathVariable Long id,
                                            @RequestBody SectionRequestDto sectionRequestDto) {
        return taskService.replaceTaskToOtherSection(id, sectionRequestDto);
    }

    @DeleteMapping("/{id}")
    public TaskResponseDto deleteTaskById(@PathVariable Long id) {
        return taskService.deleteTaskById(id);
    }
}
