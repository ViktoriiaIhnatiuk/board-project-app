package com.example.plannerapp.service.impl;

import com.example.plannerapp.model.Board;
import com.example.plannerapp.repository.BoardRepository;
import com.example.plannerapp.service.BoardService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public List<Board> getAll() {
        return boardRepository.findAll();
    }

    @Override
    public Board getById(Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find board by id: " + id));
    }

    @Override
    public Board delete(Long id) {
        Board deletedBoard = boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't delete board by id: " + id));
        boardRepository.deleteById(id);
        return deletedBoard;
    }

    @Override
    public Board getBoardByName(String name) {
        return boardRepository.getBoardByName(name);
    }
}
