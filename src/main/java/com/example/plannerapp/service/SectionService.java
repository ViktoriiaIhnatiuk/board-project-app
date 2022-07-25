package com.example.plannerapp.service;

import com.example.plannerapp.model.Section;

import java.util.List;

public interface SectionService {
    Section createSection(Section section);

    List<Section> getAllSections();

    Section getSectionById(Long id);

    Section getSectionByName(String name);

    void deleteSectionById(Long id);
}
