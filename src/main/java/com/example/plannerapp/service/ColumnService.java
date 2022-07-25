package com.example.plannerapp.service;

import com.example.plannerapp.model.Collumn;

import java.util.List;

public interface ColumnService {
    Collumn createColumn(Collumn collumn);

    List<Collumn> getAll();

    Collumn getById(Long id);

    Collumn getColumnByName(String name);

    void delete(Long id);
}
