package com.example.plannerapp.controller;

import com.example.plannerapp.dto.request.BoardRequestDto;
import com.example.plannerapp.dto.request.SectionRequestDto;
import com.example.plannerapp.dto.response.BoardResponseDto;
import com.example.plannerapp.dto.response.BoardWithColumnsResponseDto;
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
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    private final RequestDtoMapper<BoardRequestDto, Board>
            requestDtoMapper;
    private final ResponseDtoMapper<BoardResponseDto, Board>
    responseDtoMapper;
    private final SectionService sectionService;
    private final ResponseDtoMapper<BoardWithColumnsResponseDto, Board> boardWithColumnsResponseDtoMapper;
    public BoardController(BoardService boardService,
                           RequestDtoMapper<BoardRequestDto, Board> requestDtoMapper,
                           ResponseDtoMapper<BoardResponseDto, Board> responseDtoMapper,
                           SectionService sectionService,
                           ResponseDtoMapper<BoardWithColumnsResponseDto, Board> boardWithColumnsResponseDtoMapper) {
        this.boardService = boardService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
        this.sectionService = sectionService;
        this.boardWithColumnsResponseDtoMapper = boardWithColumnsResponseDtoMapper;
    }
    @PostMapping
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        return responseDtoMapper.mapToDto(boardService.createBoard(
                requestDtoMapper.mapToModel(boardRequestDto)));
    }

    @GetMapping
    public List<BoardResponseDto> getAllBoards() {
        return boardService.getAll().stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BoardResponseDto getBoardById(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(boardService.getById(id));
    }

    @PutMapping("/rename/{id}")
    public BoardResponseDto renameBoardById(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
        Board board = boardService.getById(id);
        board.setName(boardRequestDto.getName());
        Board updatedBoard = boardService.createBoard(board);
        return responseDtoMapper.mapToDto(updatedBoard);
    }

    @PutMapping("/{id}")
    public BoardResponseDto addSectionToBoardById(@PathVariable Long id,
                                                 @RequestBody List<Long> columnsIds) {
        Board board = boardService.getById(id);
        List<Long> currentSections = board.getSections().stream()
                .map(e -> e.getId())
                .collect(Collectors.toList());
        currentSections.addAll(columnsIds);
        List<Section> updatedSections = currentSections.stream()
                        .map(sectionService::getSectionById)
                .collect(Collectors.toList());
        board.setSections(updatedSections);
        Board updatedBoard = boardService.createBoard(board);
        return responseDtoMapper.mapToDto(updatedBoard);
    }

    @PutMapping("/change-picture/{id}")
    public BoardResponseDto changePictureToBoardById(@PathVariable Long id,
                                                 @RequestBody String path) {
        Board board = boardService.getById(id);
        board.setBackgroundImagePath(path);
        Board updatedBoard = boardService.createBoard(board);
        return responseDtoMapper.mapToDto(updatedBoard);
    }

    @DeleteMapping("/{id}")
    public BoardResponseDto deleteBoardById(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(boardService.delete(id));
    }

    @GetMapping("/{id}/read-full")
    public BoardWithColumnsResponseDto getFullBoardInfoById(@PathVariable Long id) {
        return boardWithColumnsResponseDtoMapper.mapToDto(boardService.getById(id));
    }

    @PutMapping("/{id}/remove-section")
    public String removeSectionFromBoard(@PathVariable Long id, @RequestBody SectionRequestDto sectionRequestDto) {
        Board board = boardService.getById(id);
        List<Section> sections = board.getSections();
        Section section = sectionService.getSectionByName(sectionRequestDto.getName());
        List <Board> boards = section.getBoards();
        Board updatedBoard = new Board();
        Section updatedSection = new Section();
        if (board != null && section != null) {
            sections.remove(section);
            board.setSections(sections);
            updatedBoard = boardService.createBoard(board);
            boards.remove(board);
            updatedSection = sectionService.createSection(section);
        }
        if (updatedBoard == null) {
            throw new RuntimeException("Can't remove section from board with id: " + id);
        }
        if (updatedSection == null) {
            throw new RuntimeException("Can't update section after removing from board with id: " + id);
        }
        return "Successful";
    }

}
