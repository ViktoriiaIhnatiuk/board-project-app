package com.example.boardprojectapp.mapper;

import com.example.boardprojectapp.dto.request.TaskRequestDto;
import com.example.boardprojectapp.dto.response.TaskResponseDto;
import com.example.boardprojectapp.model.Task;
import com.example.boardprojectapp.service.ColumnService;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper implements RequestDtoMapper <TaskRequestDto, Task>,
ResponseDtoMapper<TaskResponseDto, Task> {
    private final ColumnService columnService;

    public TaskMapper(ColumnService columnService) {
        this.columnService = columnService;
    }

    @Override
    public Task mapToModel(TaskRequestDto dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setColumn(columnService.getById(dto.getColumnId()));
        return task;
    }

    @Override
    public TaskResponseDto mapToDto(Task model) {
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setId(model.getId());
        taskResponseDto.setTitle(model.getTitle());
        taskResponseDto.setDescription(model.getDescription());
        taskResponseDto.setColumnId(model.getColumn().getId());
        return taskResponseDto;
    }
}
