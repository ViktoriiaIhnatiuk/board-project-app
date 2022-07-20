package com.example.boardprojectapp.mapper;

import com.example.boardprojectapp.dto.request.BoardRequestDto;
import com.example.boardprojectapp.dto.response.BoardResponseDto;
import com.example.boardprojectapp.model.Board;
import com.example.boardprojectapp.model.Column;
import com.example.boardprojectapp.service.ColumnService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BoardMapper implements RequestDtoMapper<BoardRequestDto, Board>,
    ResponseDtoMapper<BoardResponseDto, Board> {
    private final ColumnService columnService;

    public BoardMapper(ColumnService columnService) {
        this.columnService = columnService;
    }

    @Override
    public Board mapToModel(BoardRequestDto dto) {
        Board board = new Board();
        board.setName(dto.getName());
        board.setBackgroundImagePath(dto.getBackgroundImagePath());
        board.setColumns(dto.getColumnsIds().stream()
                .map(columnService::getById)
                .collect(Collectors.toList()));
        return board;
    }

    @Override
    public BoardResponseDto mapToDto(Board model) {
        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setId(model.getId());
        boardResponseDto.setBackgroundImagePath(model.getBackgroundImagePath());
        boardResponseDto.setName(model.getName());
        boardResponseDto.setColumnsIds(model.getColumns().stream()
                .map(Column::getId)
                .collect(Collectors.toList()));
        return boardResponseDto;
    }
}
