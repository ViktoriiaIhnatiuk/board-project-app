package com.example.plannerapp.controller;

import com.example.plannerapp.dto.request.ColumnRequestDto;
import com.example.plannerapp.dto.request.TaskRequestDto;
import com.example.plannerapp.dto.response.TaskResponseDto;
import com.example.plannerapp.mapper.RequestDtoMapper;
import com.example.plannerapp.mapper.ResponseDtoMapper;
import com.example.plannerapp.model.Collumn;
import com.example.plannerapp.model.Task;
import com.example.plannerapp.service.ColumnService;
import com.example.plannerapp.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final ColumnService columnService;
    private final RequestDtoMapper<TaskRequestDto, Task> requestDtoMapper;
    private final ResponseDtoMapper<TaskResponseDto, Task> responseDtoMapper;

    private final RequestDtoMapper<ColumnRequestDto, Collumn> columnRequestDtoMapper;

    public TaskController(TaskService taskService,
                          ColumnService columnService,
                          RequestDtoMapper<TaskRequestDto, Task> requestDtoMapper,
                          ResponseDtoMapper<TaskResponseDto, Task> responseDtoMapper,
                          RequestDtoMapper<ColumnRequestDto, Collumn> columnRequestDtoMapper) {
        this.taskService = taskService;
        this.columnService = columnService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
        this.columnRequestDtoMapper = columnRequestDtoMapper;
    }

    @PostMapping("/columns/{id}")
    private TaskResponseDto createTask(@PathVariable Long id, @RequestBody TaskRequestDto taskRequestDto) {
        Task task = new Task();
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        Collumn collumn = columnService.getById(id);
        task.setColumn(collumn);
        Task createdTask = taskService.createTask(task);
        if (createdTask != null) {
            List<Task> tasks = collumn.getTasks();
            tasks.add(createdTask);
            columnService.createColumn(collumn);
            collumn.setTasks(tasks);
            columnService.createColumn(collumn);
        }
        return responseDtoMapper.mapToDto(createdTask);
    }

    @GetMapping
    public List<TaskResponseDto> getAll(){
        return (taskService.findAll().stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public TaskResponseDto getById(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(taskService.findById(id));
    }

    @PutMapping("/{id}")
    public TaskResponseDto update(@PathVariable Long id,
                                  @RequestBody TaskRequestDto taskRequestDto) {
        Task task = taskService.findById(id);
        if (taskRequestDto.getTitle() != null) {
            task.setTitle(taskRequestDto.getTitle());
        }
        if (taskRequestDto.getDescription() != null) {
            task.setDescription(taskRequestDto.getDescription());
        }
        Task updatedTask = taskService.createTask(task);
        return responseDtoMapper.mapToDto(updatedTask);
    }

    @PutMapping("/{id}/status-update")
    public TaskResponseDto updateTaskStatus(@PathVariable Long id,
                                            @RequestBody ColumnRequestDto columnRequestDto) {
        Task task = taskService.findById(id);
        Collumn collumn = columnService.getCollumnByName(columnRequestDto.getName());
        task.setColumn(columnService.getById(collumn.getId()));
        Task updatedTask = taskService.createTask(task);
        return responseDtoMapper.mapToDto(updatedTask);
    }

    @DeleteMapping("/{id}")
    public TaskResponseDto delete(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(taskService.delete(id));
    }
}
