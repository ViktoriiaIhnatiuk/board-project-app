package com.example.plannerapp.mapper;

import com.example.plannerapp.dto.response.BoardWithSectionsResponseDto;
import com.example.plannerapp.dto.response.SectionWithTasksResponseDto;
import com.example.plannerapp.model.Board;
import com.example.plannerapp.model.Section;
import com.example.plannerapp.service.SectionService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class BoardWithSectionsMapper implements ResponseDtoMapper<BoardWithSectionsResponseDto, Board> {
    private final ResponseDtoMapper<SectionWithTasksResponseDto, Section> sectionResponseDtoWithTasks;

    public BoardWithSectionsMapper(ResponseDtoMapper<SectionWithTasksResponseDto, Section> sectionResponseDtoWithTasks,
                                   SectionService sectionService) {
        this.sectionResponseDtoWithTasks = sectionResponseDtoWithTasks;
    }

    @Override
    public BoardWithSectionsResponseDto mapToDto(Board model) {
        BoardWithSectionsResponseDto boardWithSectionsResponseDto = new BoardWithSectionsResponseDto();
        boardWithSectionsResponseDto.setId(model.getId());
        boardWithSectionsResponseDto.setBackgroundImagePath(model.getBackgroundImagePath());
        boardWithSectionsResponseDto.setName(model.getName());
        if (model.getSections() != null) {
            boardWithSectionsResponseDto.setSections(model.getSections().stream()
                    .map(sectionResponseDtoWithTasks:: mapToDto)
                    .collect(Collectors.toList()));
        }
        return boardWithSectionsResponseDto;
    }
}
