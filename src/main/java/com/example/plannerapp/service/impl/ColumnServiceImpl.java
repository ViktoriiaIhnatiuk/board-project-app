package com.example.plannerapp.service.impl;

import com.example.plannerapp.model.Collumn;
import com.example.plannerapp.repository.ColumnRepository;
import com.example.plannerapp.service.ColumnService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnServiceImpl implements ColumnService {
    private final ColumnRepository columnRepository;

    public ColumnServiceImpl(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    @Override
    public Collumn createColumn(Collumn collumn) {
        return columnRepository.save(collumn);
    }

    @Override
    public List<Collumn> getAll() {
        return columnRepository.findAll();
    }

    @Override
    public Collumn getById(Long id) {
        return columnRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find column by id: " + id));
    }

    @Override
    public Collumn getCollumnByName(String name) {
        return columnRepository.getCollumnByName(name);
    }

    @Override
    public void delete(Long id) {
        columnRepository.deleteById(id);
    }
}
