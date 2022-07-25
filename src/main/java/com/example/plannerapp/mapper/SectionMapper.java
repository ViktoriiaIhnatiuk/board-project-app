package com.example.plannerapp.mapper;

import com.example.plannerapp.dto.request.SectionRequestDto;
import com.example.plannerapp.dto.response.SectionResponseDto;
import com.example.plannerapp.model.Board;
import com.example.plannerapp.model.Section;
import com.example.plannerapp.model.Task;
import com.example.plannerapp.service.BoardService;
import com.example.plannerapp.service.TaskService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SectionMapper implements RequestDtoMapper<SectionRequestDto, Section>,
        ResponseDtoMapper<SectionResponseDto, Section> {
    private final BoardService boardService;
    private final TaskService taskService;

    public SectionMapper(BoardService boardService, TaskService taskService) {
        this.boardService = boardService;
        this.taskService = taskService;
    }

    @Override
    public Section mapToModel(SectionRequestDto dto) {
        Section section = new Section();
        section.setName(dto.getName());
        return section;
    }

    @Override
    public SectionResponseDto mapToDto(Section model) {
        SectionResponseDto sectionResponseDto = new SectionResponseDto();
        sectionResponseDto.setId(model.getId());
        sectionResponseDto.setName(model.getName());
        if (model.getBoards() != null && !model.getBoards().isEmpty()) {
        sectionResponseDto.setBoardIds(model.getBoards().stream()
                .map(Board::getId)
                .collect(Collectors.toList()));
        }
        if (model.getTasks() != null) {
            sectionResponseDto.setTasksIds(model.getTasks().stream()
                    .map(Task::getId)
                    .collect(Collectors.toList()));
        }
        return sectionResponseDto;
    }
}
