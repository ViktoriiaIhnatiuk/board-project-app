package com.example.plannerapp.service;

import com.example.plannerapp.model.Board;

import java.util.List;

public interface BoardService {
    Board createBoard (Board board);

    List<Board> getAll();

    Board getById(Long id);

    Board delete (Long id);
}
