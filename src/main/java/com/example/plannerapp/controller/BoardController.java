package com.example.plannerapp.controller;

import com.example.plannerapp.dto.request.BoardRequestDto;
import com.example.plannerapp.dto.request.SectionRequestDto;
import com.example.plannerapp.dto.response.BoardResponseDto;
import com.example.plannerapp.dto.response.BoardWithSectionsResponseDto;
import com.example.plannerapp.service.BoardService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    @PostMapping
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        return boardService.createBoard(boardRequestDto);
    }

    @GetMapping
    public List<BoardResponseDto> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public BoardResponseDto getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

    @PutMapping("/rename/{id}")
    public BoardResponseDto renameBoardById(@PathVariable Long id,
                                            @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.renameBoardById(id, boardRequestDto);
    }

    @PutMapping("/{id}")
    public BoardResponseDto addSectionToBoardById(@PathVariable Long id,
                                                 @RequestBody List<Long> sectionsIds) {
        return boardService.addSectionToBoardById(id, sectionsIds);
    }

    @PutMapping("/change-picture/{id}")
    public BoardResponseDto changePictureToBoardById(@PathVariable Long id,
                                                 @RequestBody String path) {
        return boardService.changePictureToBoardById(id, path);
    }

    @DeleteMapping("/{id}")
    public BoardResponseDto deleteBoardById(@PathVariable Long id) {
        return boardService.deleteBoardById(id);
    }

    @GetMapping("/{id}/read-full")
    public BoardWithSectionsResponseDto getFullBoardInfoById(@PathVariable Long id) {
        return boardService.getFullBoardInfoById(id);
    }

    @PutMapping("/{id}/remove-section")
    public String removeSectionFromBoard(@PathVariable Long id,
                                         @RequestBody SectionRequestDto sectionRequestDto) {
        return boardService.removeSectionFromBoard(id, sectionRequestDto);
    }

}
