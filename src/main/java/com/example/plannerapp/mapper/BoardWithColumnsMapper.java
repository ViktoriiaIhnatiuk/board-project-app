package com.example.plannerapp.mapper;

import com.example.plannerapp.dto.response.BoardWithColumnsResponseDto;
import com.example.plannerapp.dto.response.SectionWithTasksResponseDto;
import com.example.plannerapp.model.Board;
import com.example.plannerapp.model.Section;
import com.example.plannerapp.service.SectionService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class BoardWithColumnsMapper implements ResponseDtoMapper<BoardWithColumnsResponseDto, Board> {
    private final ResponseDtoMapper<SectionWithTasksResponseDto, Section> columnResponseDtoWithTasks;

    public BoardWithColumnsMapper(ResponseDtoMapper<SectionWithTasksResponseDto, Section> columnResponseDtoWithTasks,
                                  SectionService sectionService) {
        this.columnResponseDtoWithTasks = columnResponseDtoWithTasks;
    }

    @Override
    public BoardWithColumnsResponseDto mapToDto(Board model) {
        BoardWithColumnsResponseDto boardWithColumnsResponseDto = new BoardWithColumnsResponseDto();
        boardWithColumnsResponseDto.setId(model.getId());
        boardWithColumnsResponseDto.setBackgroundImagePath(model.getBackgroundImagePath());
        boardWithColumnsResponseDto.setName(model.getName());
        if (model.getSections() != null) {
            boardWithColumnsResponseDto.setColumns(model.getSections().stream()
                    .map(columnResponseDtoWithTasks:: mapToDto)
                    .collect(Collectors.toList()));
        }
        return boardWithColumnsResponseDto;
    }
}
