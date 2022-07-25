package com.example.plannerapp.mapper;

import com.example.plannerapp.dto.response.SectionWithTasksResponseDto;
import com.example.plannerapp.dto.response.TaskResponseDto;
import com.example.plannerapp.model.Board;
import com.example.plannerapp.model.Section;
import com.example.plannerapp.model.Task;
import org.springframework.stereotype.Component;


import java.util.stream.Collectors;

@Component
public class SectionWithTasksMapper implements ResponseDtoMapper<SectionWithTasksResponseDto, Section>{
    private final ResponseDtoMapper<TaskResponseDto, Task> taskResponseDtoMapper;

    public SectionWithTasksMapper(ResponseDtoMapper<TaskResponseDto, Task> taskResponseDtoMapper) {
        this.taskResponseDtoMapper = taskResponseDtoMapper;
    }

    @Override
    public SectionWithTasksResponseDto mapToDto(Section model) {
        SectionWithTasksResponseDto sectionWithTasksResponseDto = new SectionWithTasksResponseDto();
        sectionWithTasksResponseDto.setId(model.getId());
        sectionWithTasksResponseDto.setName(model.getName());
        if (model.getBoards() != null && !model.getBoards().isEmpty()) {
            sectionWithTasksResponseDto.setBoardIds(model.getBoards().stream()
                    .map(Board::getId)
                    .collect(Collectors.toList()));
        }
        if (model.getTasks() != null) {
            sectionWithTasksResponseDto.setTasks(model.getTasks().stream()
                    .map(taskResponseDtoMapper::mapToDto)
                    .collect(Collectors.toList()));
        }
        return sectionWithTasksResponseDto;
    }
}
