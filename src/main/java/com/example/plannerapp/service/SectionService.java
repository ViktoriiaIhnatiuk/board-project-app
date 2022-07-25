package com.example.plannerapp.service;

import com.example.plannerapp.dto.request.BoardRequestDto;
import com.example.plannerapp.dto.request.SectionRequestDto;
import com.example.plannerapp.dto.response.DeletedSectionResponseDto;
import com.example.plannerapp.dto.response.SectionResponseDto;

import java.util.List;

public interface SectionService {
    SectionResponseDto createSection(SectionRequestDto sectionRequestDto);

    List<SectionResponseDto> getAllSections();

    SectionResponseDto getSectionById(Long id);

    SectionResponseDto getSectionByName(String name);

    DeletedSectionResponseDto deleteSectionById(Long id);

    SectionResponseDto updateSectionById(Long id, SectionRequestDto sectionRequestDto);

    SectionResponseDto setSectionToBoard(Long id, BoardRequestDto boardRequestDto);
}
