package com.example.plannerapp.dto.response;

import java.util.List;

public class SectionResponseDto {
    private Long id;
    private String name;
    private List<Long> tasksIds;
    private List<Long> boardsIds;

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

    public List<Long> getTasksIds() {
        return tasksIds;
    }

    public void setTasksIds(List<Long> tasksIds) {
        this.tasksIds = tasksIds;
    }

    public List<Long> getBoardIds() {
        return boardsIds;
    }

    public void setBoardIds(List<Long> boardIds) {
        this.boardsIds = boardIds;
    }

    @Override
    public String toString() {
        return "SectionResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tasksIds=" + tasksIds +
                ", boardId=" + boardsIds +
                '}';
    }
}
