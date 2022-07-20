package com.example.boardprojectapp.service;

import com.example.boardprojectapp.model.Column;
import org.springframework.stereotype.Controller;

import java.util.List;

public interface ColumnService {
    Column createColumn(Column column);

    List<Column> getAll();

    Column getById(Long id);

    void delete(Long id);
}
