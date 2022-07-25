package com.example.plannerapp.controller;

import com.example.plannerapp.dto.request.BoardRequestDto;
import com.example.plannerapp.dto.request.SectionRequestDto;
import com.example.plannerapp.dto.response.DeletedSectionResponseDto;
import com.example.plannerapp.dto.response.SectionResponseDto;
import com.example.plannerapp.service.SectionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sections")
public class SectionController {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @PostMapping
    public SectionResponseDto createSection(@RequestBody SectionRequestDto sectionRequestDto){
        return sectionService.createSection(sectionRequestDto);
    }

    @PutMapping("/{id}")
    public SectionResponseDto updateSectionById(@PathVariable Long id,
                                                @RequestBody SectionRequestDto sectionRequestDto){
        return sectionService.updateSectionById(id, sectionRequestDto);
    }

    @DeleteMapping("/{id}")
    public DeletedSectionResponseDto deleteSectionById(@PathVariable Long id) {
        return sectionService.deleteSectionById(id);
    }

    @GetMapping
    public List<SectionResponseDto> getAllSections() {
        return sectionService.getAllSections();
    }

    @GetMapping("/{id}")
    public SectionResponseDto getSectionById(@PathVariable Long id) {
        return sectionService.getSectionById(id);
    }

    @GetMapping("/by-name")
    public SectionResponseDto getSectionByName (@RequestParam String name) {
        return sectionService.getSectionByName(name);
    }

    @PutMapping("/{id}/set-board")
    public SectionResponseDto setSectionToBoard(@PathVariable Long id,
                                               @RequestBody BoardRequestDto boardRequestDto){

        return sectionService.setSectionToBoard(id, boardRequestDto);
    }
}
