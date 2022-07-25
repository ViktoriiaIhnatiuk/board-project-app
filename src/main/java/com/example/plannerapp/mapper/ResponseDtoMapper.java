package com.example.plannerapp.mapper;

public interface ResponseDtoMapper <D, M>{
    D mapToDto (M model);
}
