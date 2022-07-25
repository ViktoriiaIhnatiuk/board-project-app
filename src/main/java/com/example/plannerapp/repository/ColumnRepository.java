package com.example.plannerapp.repository;

import com.example.plannerapp.model.Collumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnRepository extends JpaRepository<Collumn, Long> {
  Collumn getColumnByName(String name);
}
