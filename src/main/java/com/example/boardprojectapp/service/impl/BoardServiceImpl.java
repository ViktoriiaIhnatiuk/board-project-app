package com.example.boardprojectapp.service.impl;

import com.example.boardprojectapp.model.Board;
import com.example.boardprojectapp.repository.BoardRepository;
import com.example.boardprojectapp.service.BoardService;
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
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
