package com.example.boardprojectapp.service.impl;

import com.example.boardprojectapp.model.Column;
import com.example.boardprojectapp.repository.ColumnRepository;
import com.example.boardprojectapp.service.ColumnService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnServiceImpl implements ColumnService {
    private final ColumnRepository columnRepository;

    public ColumnServiceImpl(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    @Override
    public Column createColumn(Column column) {
        return columnRepository.save(column);
    }

    @Override
    public List<Column> getAll() {
        return columnRepository.findAll();
    }

    @Override
    public Column getById(Long id) {
        return columnRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find column by id" + id));
    }

    @Override
    public void delete(Long id) {
        columnRepository.deleteById(id);
    }
}
