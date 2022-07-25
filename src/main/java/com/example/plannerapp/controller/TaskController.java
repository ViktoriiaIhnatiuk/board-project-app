package com.example.plannerapp.controller;

import com.example.plannerapp.dto.request.SectionRequestDto;
import com.example.plannerapp.dto.request.TaskRequestDto;
import com.example.plannerapp.dto.response.TaskResponseDto;
import com.example.plannerapp.mapper.RequestDtoMapper;
import com.example.plannerapp.mapper.ResponseDtoMapper;
import com.example.plannerapp.model.Section;
import com.example.plannerapp.model.Task;
import com.example.plannerapp.service.SectionService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final SectionService sectionService;
    private final RequestDtoMapper<TaskRequestDto, Task> requestDtoMapper;
    private final ResponseDtoMapper<TaskResponseDto, Task> responseDtoMapper;

    private final RequestDtoMapper<SectionRequestDto, Section> sectionRequestDtoMapper;

    public TaskController(TaskService taskService,
                          SectionService sectionService,
                          RequestDtoMapper<TaskRequestDto, Task> requestDtoMapper,
                          ResponseDtoMapper<TaskResponseDto, Task> responseDtoMapper,
                          RequestDtoMapper<SectionRequestDto, Section> sectionRequestDtoMapper) {
        this.taskService = taskService;
        this.sectionService = sectionService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
        this.sectionRequestDtoMapper = sectionRequestDtoMapper;
    }

    @PostMapping("/sections/{id}")
    private TaskResponseDto createTask(@PathVariable Long id, @RequestBody TaskRequestDto taskRequestDto) {
        Task task = new Task();
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        Section section = sectionService.getSectionById(id);
        task.setSection(section);
        Task createdTask = taskService.createTask(task);
        if (createdTask != null) {
            List<Task> tasks = section.getTasks();
            tasks.add(createdTask);
            sectionService.createSection(section);
            section.setTasks(tasks);
            sectionService.createSection(section);
        }
        return responseDtoMapper.mapToDto(createdTask);
    }

    @GetMapping
    public List<TaskResponseDto> getAllTasks(){
        return (taskService.findAll().stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public TaskResponseDto getTaskById(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(taskService.findById(id));
    }

    @PutMapping("/{id}")
    public TaskResponseDto updateTaskById(@PathVariable Long id,
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

    @PutMapping("/{id}/section-update")
    public TaskResponseDto replaceTaskToOtherSection(@PathVariable Long id,
                                            @RequestBody SectionRequestDto sectionRequestDto) {
        Task task = taskService.findById(id);
        Section column = sectionService.getSectionByName(sectionRequestDto.getName());
        if (task != null && column != null) {
            task.setSection(column);
        }
        Task updatedTask = taskService.createTask(task);
        return responseDtoMapper.mapToDto(updatedTask);
    }

    @DeleteMapping("/{id}")
    public TaskResponseDto deleteTaskById(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(taskService.delete(id));
    }
}
