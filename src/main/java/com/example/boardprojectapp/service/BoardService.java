package com.example.boardprojectapp.service;

import com.example.boardprojectapp.model.Board;

import java.util.List;

public interface BoardService {
    Board createBoard (Board board);

    List<Board> getAll();

    Board getById(Long id);

    void delete (Long id);
}
