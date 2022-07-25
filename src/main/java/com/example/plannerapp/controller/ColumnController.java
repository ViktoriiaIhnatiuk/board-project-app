package com.example.plannerapp.controller;

import com.example.plannerapp.dto.request.BoardRequestDto;
import com.example.plannerapp.dto.request.ColumnRequestDto;
import com.example.plannerapp.dto.response.ColumnResponseDto;
import com.example.plannerapp.dto.response.DeletedColumnResponseDto;
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
@RequestMapping("/columns")
public class ColumnController {
    private final ColumnService columnService;
    private final RequestDtoMapper <ColumnRequestDto, Collumn> columnRequestDtoMapper;
    private final ResponseDtoMapper<ColumnResponseDto, Collumn> columnResponseMapper;
    private final ResponseDtoMapper<DeletedColumnResponseDto, Collumn> deletedColumnResponseDtoMapper;
    private final RequestDtoMapper<BoardRequestDto, Board> boardRequestDtoMapper;
    private final BoardService boardService;

    public ColumnController(ColumnService columnService,
                            RequestDtoMapper<ColumnRequestDto, Collumn> columnRequestDtoMapper,
                            ResponseDtoMapper<ColumnResponseDto, Collumn> columnResponseMapper,
                            ResponseDtoMapper<DeletedColumnResponseDto, Collumn> deletedColumnResponseDtoMapper, RequestDtoMapper<BoardRequestDto, Board> boardRequestDtoMapper, BoardService boardService) {
        this.columnService = columnService;
        this.columnRequestDtoMapper = columnRequestDtoMapper;
        this.columnResponseMapper = columnResponseMapper;
        this.deletedColumnResponseDtoMapper = deletedColumnResponseDtoMapper;
        this.boardRequestDtoMapper = boardRequestDtoMapper;
        this.boardService = boardService;
    }

    @PostMapping
    public ColumnResponseDto createColumn(@RequestBody ColumnRequestDto columnRequestDto){
        Collumn collumn = columnService.createColumn(columnRequestDtoMapper.mapToModel(columnRequestDto));
        return columnResponseMapper.mapToDto(collumn);
    }

    @PutMapping("/{id}")
    public ColumnResponseDto updateColumn(@PathVariable Long id, @RequestBody ColumnRequestDto columnRequestDto){
        Collumn collumn = columnService.getById(id);
        collumn.setName(columnRequestDto.getName());
        columnService.createColumn(collumn);
        return columnResponseMapper.mapToDto(collumn);
    }

    @DeleteMapping("/{id}")
    public DeletedColumnResponseDto deleteColumn(@PathVariable Long id) {
        Collumn collumn = columnService.getById(id);
        columnService.delete(id);
        return deletedColumnResponseDtoMapper.mapToDto(collumn);
        // cascade deleting with tasks
    }

    @GetMapping
    public List<ColumnResponseDto> getAllColumns() {
        return columnService.getAll().stream()
                .map(columnResponseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ColumnResponseDto getColumnBuId(@PathVariable Long id) {
        return columnResponseMapper.mapToDto(columnService.getById(id));
    }

    @GetMapping("/by-name")
    public ColumnResponseDto getCollumnByName (@RequestParam String name) {
        return columnResponseMapper.mapToDto(columnService.getColumnByName(name));
    }

    @PutMapping("/{id}/set-board")
    public ColumnResponseDto setColumnToBoard(@PathVariable Long id,
                                              @RequestBody BoardRequestDto boardRequestDto){
        Collumn column = columnService.getById(id);
        Board board = boardService.getBoardByName(boardRequestDto.getName());
        List<Collumn> columns = board.getColumns();
        List <Board> boards = column.getBoards();
        Collumn updatedColumn = new Collumn();
        if (column != null && board != null) {
            boards.add(board);
            column.setBoards(boards);
            updatedColumn = columnService.createColumn(column);
            columns.add(column);
            boardService.createBoard(board);
        }
        return columnResponseMapper.mapToDto(column);
    }
}
