package com.example.plannerapp.mapper;

import com.example.plannerapp.dto.request.ColumnRequestDto;
import com.example.plannerapp.dto.response.ColumnResponseDto;
import com.example.plannerapp.model.Collumn;
import com.example.plannerapp.model.Task;
import com.example.plannerapp.service.BoardService;
import com.example.plannerapp.service.TaskService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ColumnMapper implements RequestDtoMapper<ColumnRequestDto, Collumn>,
        ResponseDtoMapper<ColumnResponseDto, Collumn> {
    private final BoardService boardService;
    private final TaskService taskService;

    public ColumnMapper(BoardService boardService, TaskService taskService) {
        this.boardService = boardService;
        this.taskService = taskService;
    }

    @Override
    public Collumn mapToModel(ColumnRequestDto dto) {
        Collumn collumn = new Collumn();
        collumn.setName(dto.getName());
        return collumn;
    }

    @Override
    public ColumnResponseDto mapToDto(Collumn model) {
        ColumnResponseDto columnResponseDto = new ColumnResponseDto();
        columnResponseDto.setId(model.getId());
        columnResponseDto.setName(model.getName());
        if (model.getBoard() != null) {
        columnResponseDto.setBoardId(model.getBoard().getId());
        }
        if (model.getTasks() != null) {
            columnResponseDto.setTasksIds(model.getTasks().stream()
                    .map(Task::getId)
                    .collect(Collectors.toList()));
        }
        return columnResponseDto;
    }
}
