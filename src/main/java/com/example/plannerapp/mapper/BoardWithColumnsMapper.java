package com.example.plannerapp.mapper;

import com.example.plannerapp.dto.response.BoardWithColumnsResponseDto;
import com.example.plannerapp.dto.response.ColumnResponseDtoWithTasks;
import com.example.plannerapp.model.Board;
import com.example.plannerapp.model.Collumn;
import com.example.plannerapp.service.ColumnService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class BoardWithColumnsMapper implements ResponseDtoMapper<BoardWithColumnsResponseDto, Board> {
    private ResponseDtoMapper<ColumnResponseDtoWithTasks, Collumn> columnResponseDtoWithTasks;
    private final ColumnService columnService;

    public BoardWithColumnsMapper(ResponseDtoMapper<ColumnResponseDtoWithTasks, Collumn> columnResponseDtoWithTasks,
                                  ColumnService columnService) {
        this.columnResponseDtoWithTasks = columnResponseDtoWithTasks;
        this.columnService = columnService;
    }

    @Override
    public BoardWithColumnsResponseDto mapToDto(Board model) {
        BoardWithColumnsResponseDto boardWithColumnsResponseDto = new BoardWithColumnsResponseDto();
        boardWithColumnsResponseDto.setId(model.getId());
        boardWithColumnsResponseDto.setBackgroundImagePath(model.getBackgroundImagePath());
        boardWithColumnsResponseDto.setName(model.getName());
        if (model.getColumns() != null) {
            boardWithColumnsResponseDto.setColumns(model.getColumns().stream()
                    .map(columnResponseDtoWithTasks:: mapToDto)
                    .collect(Collectors.toList()));
        }
        return boardWithColumnsResponseDto;
    }
}
