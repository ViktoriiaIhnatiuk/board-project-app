package com.example.plannerapp.repository;

import com.example.plannerapp.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board getBoardByName(String name);
}
