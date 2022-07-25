package com.example.plannerapp.service.impl;

import com.example.plannerapp.model.Section;
import com.example.plannerapp.repository.SectionRepository;
import com.example.plannerapp.service.SectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;

    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Section createSection(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    @Override
    public Section getSectionById(Long id) {
        return sectionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find column by id: " + id));
    }

    @Override
    public Section getSectionByName(String name) {
        return sectionRepository.getSectionByName(name);
    }

    @Override
    public void deleteSectionById(Long id) {
        sectionRepository.deleteById(id);
    }
}
