package com.example.plannerapp.mapper;

import com.example.plannerapp.dto.response.ColumnResponseDtoWithTasks;
import com.example.plannerapp.dto.response.TaskResponseDto;
import com.example.plannerapp.model.Board;
import com.example.plannerapp.model.Collumn;
import com.example.plannerapp.model.Task;
import org.springframework.stereotype.Component;


import java.util.stream.Collectors;

@Component
public class ColumnWithTasksMapper implements ResponseDtoMapper<ColumnResponseDtoWithTasks, Collumn>{
    private final ResponseDtoMapper<TaskResponseDto, Task> taskResponseDtoMapper;

    public ColumnWithTasksMapper(ResponseDtoMapper<TaskResponseDto, Task> taskResponseDtoMapper) {
        this.taskResponseDtoMapper = taskResponseDtoMapper;
    }

    @Override
    public ColumnResponseDtoWithTasks mapToDto(Collumn model) {
        ColumnResponseDtoWithTasks columnResponseDtoWithTasks= new ColumnResponseDtoWithTasks();
        columnResponseDtoWithTasks.setId(model.getId());
        columnResponseDtoWithTasks.setName(model.getName());
        if (model.getBoards() != null && !model.getBoards().isEmpty()) {
            columnResponseDtoWithTasks.setBoardIds(model.getBoards().stream()
                    .map(Board::getId)
                    .collect(Collectors.toList()));
        }
        if (model.getTasks() != null) {
            columnResponseDtoWithTasks.setTasks(model.getTasks().stream()
                    .map(e -> taskResponseDtoMapper.mapToDto(e))
                    .collect(Collectors.toList()));
        }
        return columnResponseDtoWithTasks;
    }
}
