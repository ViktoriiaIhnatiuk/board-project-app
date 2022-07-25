package com.example.plannerapp.controller;

import com.example.plannerapp.dto.request.BoardRequestDto;
import com.example.plannerapp.dto.response.BoardResponseDto;
import com.example.plannerapp.mapper.RequestDtoMapper;
import com.example.plannerapp.mapper.ResponseDtoMapper;
import com.example.plannerapp.model.Board;
import com.example.plannerapp.model.Collumn;
import com.example.plannerapp.service.BoardService;
import com.example.plannerapp.service.ColumnService;
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
    private final ColumnService columnService;

    public BoardController(BoardService boardService,
                           RequestDtoMapper<BoardRequestDto, Board> requestDtoMapper,
                           ResponseDtoMapper<BoardResponseDto, Board> responseDtoMapper,
                           ColumnService columnService) {
        this.boardService = boardService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
        this.columnService = columnService;
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
    public BoardResponseDto addColumnToBoardById(@PathVariable Long id,
                                                 @RequestBody List<Long> columnsIds) {
        Board board = boardService.getById(id);
        List<Long> currentColumns = board.getColumns().stream()
                .map(e -> e.getId())
                .collect(Collectors.toList());
        currentColumns.addAll(columnsIds);
        List<Collumn> updatedCollumns = currentColumns.stream()
                        .map(columnService::getById)
                .collect(Collectors.toList());
        board.setColumns(updatedCollumns);
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
}
