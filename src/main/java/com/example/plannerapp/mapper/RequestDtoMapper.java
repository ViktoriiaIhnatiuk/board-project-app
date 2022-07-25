package com.example.plannerapp.mapper;

public interface RequestDtoMapper<D, M>{
    M mapToModel(D dto);
}
