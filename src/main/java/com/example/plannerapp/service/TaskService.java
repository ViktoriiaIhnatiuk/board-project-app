package com.example.plannerapp.service;

import com.example.plannerapp.dto.request.SectionRequestDto;
import com.example.plannerapp.dto.request.TaskRequestDto;
import com.example.plannerapp.dto.response.TaskResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TaskService {
    TaskResponseDto createTask(Long id, TaskRequestDto taskRequestDto);

    List<TaskResponseDto> getAllTasks();

    TaskResponseDto getTaskById(Long id);

    TaskResponseDto deleteTaskById(Long id);

    TaskResponseDto updateTaskById(Long id, TaskRequestDto taskRequestDto);

    TaskResponseDto replaceTaskToOtherSection(Long id, SectionRequestDto sectionRequestDto);
}
