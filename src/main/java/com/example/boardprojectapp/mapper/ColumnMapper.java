package com.example.boardprojectapp.mapper;

import com.example.boardprojectapp.dto.request.ColumnRequestDto;
import com.example.boardprojectapp.dto.response.ColumnResponseDto;
import com.example.boardprojectapp.model.Column;
import com.example.boardprojectapp.model.Task;
import com.example.boardprojectapp.service.BoardService;
import com.example.boardprojectapp.service.TaskService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ColumnMapper implements RequestDtoMapper<ColumnRequestDto, Column>,
        ResponseDtoMapper<ColumnResponseDto, Column> {
    private final BoardService boardService;
    private final TaskService taskService;

    public ColumnMapper(BoardService boardService, TaskService taskService) {
        this.boardService = boardService;
        this.taskService = taskService;
    }

    @Override
    public Column mapToModel(ColumnRequestDto dto) {
        Column column = new Column();
        column.setName(dto.getName());
        column.setBoard(boardService.getById(dto.getBoardId()));
        column.setTasks(dto.getTasksIds().stream()
                .map(taskService:: findById)
                .collect(Collectors.toList()));
        return column;
    }

    @Override
    public ColumnResponseDto mapToDto(Column model) {
        ColumnResponseDto columnResponseDto = new ColumnResponseDto();
        columnResponseDto.setId(model.getId());
        columnResponseDto.setBoardId(model.getBoard().getId());
        columnResponseDto.setTasksIds(model.getTasks().stream()
                .map(Task::getId)
                .collect(Collectors.toList()));
        return columnResponseDto;
    }
}
