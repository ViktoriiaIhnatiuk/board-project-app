package com.example.plannerapp.controller;

import com.example.plannerapp.dto.request.BoardRequestDto;
import com.example.plannerapp.dto.request.SectionRequestDto;
import com.example.plannerapp.dto.response.DeletedSectionResponseDto;
import com.example.plannerapp.dto.response.SectionResponseDto;
import com.example.plannerapp.mapper.RequestDtoMapper;
import com.example.plannerapp.mapper.ResponseDtoMapper;
import com.example.plannerapp.model.Board;
import com.example.plannerapp.model.Section;
import com.example.plannerapp.service.BoardService;
import com.example.plannerapp.service.SectionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sections")
public class SectionController {
    private final SectionService sectionService;
    private final RequestDtoMapper <SectionRequestDto, Section> sectionRequestDtoMapper;
    private final ResponseDtoMapper<SectionResponseDto, Section> sectionResponseDtoMapper;
    private final ResponseDtoMapper<DeletedSectionResponseDto, Section> deletedSectionResponseDtoMapper;
    private final BoardService boardService;

    public SectionController(SectionService sectionService,
                             RequestDtoMapper<SectionRequestDto, Section> sectionRequestDtoMapper,
                             ResponseDtoMapper<SectionResponseDto, Section> sectionResponseDtoMapper,
                             ResponseDtoMapper<DeletedSectionResponseDto, Section> deletedSectionResponseDtoMapper,
                             BoardService boardService) {
        this.sectionService = sectionService;
        this.sectionRequestDtoMapper = sectionRequestDtoMapper;
        this.sectionResponseDtoMapper = sectionResponseDtoMapper;
        this.deletedSectionResponseDtoMapper = deletedSectionResponseDtoMapper;
        this.boardService = boardService;
    }


    @PostMapping
    public SectionResponseDto createSection(@RequestBody SectionRequestDto sectionRequestDto){
        Section section = sectionService.createSection(sectionRequestDtoMapper.mapToModel(sectionRequestDto));
        return sectionResponseDtoMapper.mapToDto(section);
    }

    @PutMapping("/{id}")
    public SectionResponseDto updateSectionById(@PathVariable Long id, @RequestBody SectionRequestDto sectionRequestDto){
        Section section = sectionService.getSectionById(id);
        section.setName(sectionRequestDto.getName());
        sectionService.createSection(section);
        return sectionResponseDtoMapper.mapToDto(section);
    }

    @DeleteMapping("/{id}")
    public DeletedSectionResponseDto deleteSectionById(@PathVariable Long id) {
        Section section = sectionService.getSectionById(id);
        sectionService.deleteSectionById(id);
        return deletedSectionResponseDtoMapper.mapToDto(section);
    }

    @GetMapping
    public List<SectionResponseDto> getAllSections() {
        return sectionService.getAllSections().stream()
                .map(sectionResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SectionResponseDto getSectionById(@PathVariable Long id) {
        return sectionResponseDtoMapper.mapToDto(sectionService.getSectionById(id));
    }

    @GetMapping("/by-name")
    public SectionResponseDto getSectionByName (@RequestParam String name) {
        return sectionResponseDtoMapper.mapToDto(sectionService.getSectionByName(name));
    }

    @PutMapping("/{id}/set-board")
    public SectionResponseDto setSectionToBoard(@PathVariable Long id,
                                               @RequestBody BoardRequestDto boardRequestDto){
        Section section = sectionService.getSectionById(id);
        Board board = boardService.getBoardByName(boardRequestDto.getName());
        List<Section> sections = board.getSections();
        List <Board> boards = section.getBoards();
        Section updatedSection = new Section();
        if (section != null && board != null) {
            boards.add(board);
            section.setBoards(boards);
            updatedSection = sectionService.createSection(section);
            sections.add(section);
            boardService.createBoard(board);
        }
        return sectionResponseDtoMapper.mapToDto(section);
    }
}
