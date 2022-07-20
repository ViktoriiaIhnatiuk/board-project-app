package com.example.boardprojectapp.mapper;

public interface RequestDtoMapper<D, M>{
    M mapToModel(D dto);
}
