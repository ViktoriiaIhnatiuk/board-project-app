package com.example.plannerapp.dto.response;

import java.util.List;

public class BoardWithSectionsResponseDto {
    private Long id;
    private String name;
    private String backgroundImagePath;
    private List<SectionWithTasksResponseDto> sections;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackgroundImagePath() {
        return backgroundImagePath;
    }

    public void setBackgroundImagePath(String backgroundImagePath) {
        this.backgroundImagePath = backgroundImagePath;
    }

    public List<SectionWithTasksResponseDto> getSections() {
        return sections;
    }

    public void setSections(List<SectionWithTasksResponseDto> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "BoardResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", backgroundImagePath='" + backgroundImagePath + '\'' +
                ", columnsIds=" + sections +
                '}';
    }
}
