package com.example.plannerapp.controller;

import com.example.plannerapp.dto.request.ColumnRequestDto;
import com.example.plannerapp.dto.response.ColumnResponseDto;
import com.example.plannerapp.dto.response.DeletedColumnResponseDto;
import com.example.plannerapp.mapper.RequestDtoMapper;
import com.example.plannerapp.mapper.ResponseDtoMapper;
import com.example.plannerapp.model.Collumn;
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

    public ColumnController(ColumnService columnService,
                            RequestDtoMapper<ColumnRequestDto, Collumn> columnRequestDtoMapper,
                            ResponseDtoMapper<ColumnResponseDto, Collumn> columnResponseMapper,
                            ResponseDtoMapper<DeletedColumnResponseDto, Collumn> deletedColumnResponseDtoMapper) {
        this.columnService = columnService;
        this.columnRequestDtoMapper = columnRequestDtoMapper;
        this.columnResponseMapper = columnResponseMapper;
        this.deletedColumnResponseDtoMapper = deletedColumnResponseDtoMapper;
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
        return columnResponseMapper.mapToDto(columnService.getCollumnByName(name));
    }

}
