package com.example.plannerapp.service.impl;

import com.example.plannerapp.dto.request.SectionRequestDto;
import com.example.plannerapp.dto.request.TaskRequestDto;
import com.example.plannerapp.dto.response.TaskResponseDto;
import com.example.plannerapp.mapper.ResponseDtoMapper;
import com.example.plannerapp.model.Section;
import com.example.plannerapp.model.Task;
import com.example.plannerapp.repository.SectionRepository;
import com.example.plannerapp.repository.TaskRepository;
import com.example.plannerapp.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
   private final TaskRepository taskRepository;
    private final SectionRepository sectionRepository;
    private final ResponseDtoMapper<TaskResponseDto, Task> responseDtoMapper;

    public TaskServiceImpl(TaskRepository taskRepository,
                           SectionRepository sectionRepository,
                           ResponseDtoMapper<TaskResponseDto, Task> responseDtoMapper) {
        this.taskRepository = taskRepository;
        this.sectionRepository = sectionRepository;
        this.responseDtoMapper = responseDtoMapper;
    }

    @Override
    public TaskResponseDto createTask(Long id, TaskRequestDto taskRequestDto) {
        Task task = new Task();
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        Section section = sectionRepository.getById(id);
        task.setSection(section);
        Task createdTask = taskRepository.save(task);
        if (createdTask != null) {
            List<Task> tasks = section.getTasks();
            tasks.add(createdTask);
            sectionRepository.save(section);
            section.setTasks(tasks);
            sectionRepository.save(section);
        }
        return responseDtoMapper.mapToDto(createdTask);
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDto getTaskById(Long id) {
        return responseDtoMapper.mapToDto(taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find task by id: " + id)));
    }

    @Override
    public TaskResponseDto deleteTaskById(Long id) {
        Task taskToDelete = taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't delete task by id: " + id));
        taskRepository.deleteById(id);
        return responseDtoMapper.mapToDto(taskToDelete);
    }

    @Override
    public TaskResponseDto updateTaskById(Long id, TaskRequestDto taskRequestDto) {
        Task task = taskRepository.getById(id);
        if (taskRequestDto.getTitle() != null) {
            task.setTitle(taskRequestDto.getTitle());
        }
        if (taskRequestDto.getDescription() != null) {
            task.setDescription(taskRequestDto.getDescription());
        }
        Task updatedTask = taskRepository.save(task);
        return responseDtoMapper.mapToDto(updatedTask);
    }

    @Override
    public TaskResponseDto replaceTaskToOtherSection(Long id, SectionRequestDto sectionRequestDto) {
        Task task = taskRepository.getById(id);
        Section section = sectionRepository.getSectionByName(sectionRequestDto.getName());
        if (task != null && section != null) {
            task.setSection(section);
        }
        Task updatedTask = taskRepository.save(task);
        return responseDtoMapper.mapToDto(updatedTask);
    }
}
