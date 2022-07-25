package com.example.plannerapp.service.impl;

import com.example.plannerapp.dto.request.BoardRequestDto;
import com.example.plannerapp.dto.request.SectionRequestDto;
import com.example.plannerapp.dto.response.DeletedSectionResponseDto;
import com.example.plannerapp.dto.response.SectionResponseDto;
import com.example.plannerapp.mapper.RequestDtoMapper;
import com.example.plannerapp.mapper.ResponseDtoMapper;
import com.example.plannerapp.model.Board;
import com.example.plannerapp.model.Section;
import com.example.plannerapp.repository.BoardRepository;
import com.example.plannerapp.repository.SectionRepository;
import com.example.plannerapp.service.SectionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final RequestDtoMapper<SectionRequestDto, Section> sectionRequestDtoMapper;
    private final ResponseDtoMapper<SectionResponseDto, Section> sectionResponseDtoMapper;
    private final ResponseDtoMapper<DeletedSectionResponseDto, Section> deletedSectionResponseDtoMapper;
    private final BoardRepository boardRepository;

    public SectionServiceImpl(SectionRepository sectionRepository, RequestDtoMapper<SectionRequestDto, Section> sectionRequestDtoMapper, ResponseDtoMapper<SectionResponseDto, Section> sectionResponseDtoMapper, ResponseDtoMapper<DeletedSectionResponseDto, Section> deletedSectionResponseDtoMapper, BoardRepository boardRepository) {
        this.sectionRepository = sectionRepository;
        this.sectionRequestDtoMapper = sectionRequestDtoMapper;
        this.sectionResponseDtoMapper = sectionResponseDtoMapper;
        this.deletedSectionResponseDtoMapper = deletedSectionResponseDtoMapper;
        this.boardRepository = boardRepository;
    }

    @Override
    public SectionResponseDto createSection(SectionRequestDto sectionRequestDto) {
        return sectionResponseDtoMapper.mapToDto(sectionRepository.save(
                sectionRequestDtoMapper.mapToModel(sectionRequestDto)));
    }

    @Override
    public List<SectionResponseDto> getAllSections() {
        return sectionRepository.findAll().stream()
                .map(sectionResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SectionResponseDto getSectionById(Long id) {
        return sectionResponseDtoMapper.mapToDto(sectionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find column by id: " + id)));
    }

    @Override
    public SectionResponseDto getSectionByName(String name) {
        return sectionResponseDtoMapper.mapToDto(sectionRepository.getSectionByName(name));
    }

    @Override
    public DeletedSectionResponseDto deleteSectionById(Long id) {
        Section section = sectionRepository.getById(id);
        sectionRepository.deleteById(id);
        return deletedSectionResponseDtoMapper.mapToDto(section);
    }

    @Override
    public SectionResponseDto updateSectionById(Long id, SectionRequestDto sectionRequestDto) {
        Section section = sectionRepository.getById(id);
        section.setName(sectionRequestDto.getName());
        return sectionResponseDtoMapper.mapToDto(sectionRepository.save(section));
    }

    @Override
    public SectionResponseDto setSectionToBoard(Long id, BoardRequestDto boardRequestDto) {
        Section section = sectionRepository.getById(id);
        Board board = boardRepository.getBoardByName(boardRequestDto.getName());
        List<Section> sections = board.getSections();
        List <Board> boards = section.getBoards();
        Section updatedSection = new Section();
        if (section != null && board != null) {
            boards.add(board);
            section.setBoards(boards);
            updatedSection = sectionRepository.save(section);
            sections.add(section);
            boardRepository.save(board);
        }
        return sectionResponseDtoMapper.mapToDto(updatedSection);
    }
}
