package com.example.plannerapp.dto.response;

import java.util.List;

public class BoardResponseDto {
    private Long id;
    private String name;
    private String backgroundImagePath;
    private List<Long> columnsIds;

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

    public List<Long> getColumnsIds() {
        return columnsIds;
    }

    public void setColumnsIds(List<Long> columnsIds) {
        this.columnsIds = columnsIds;
    }

    @Override
    public String toString() {
        return "BoardResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", backgroundImagePath='" + backgroundImagePath + '\'' +
                ", columnsIds=" + columnsIds +
                '}';
    }
}
