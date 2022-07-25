package com.example.plannerapp.dto.response;

import java.util.List;

public class ColumnResponseDto {
    private Long id;
    private String name;
    private List<Long> tasksIds;
    private List<Long> boardIds;

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
        return boardIds;
    }

    public void setBoardIds(List<Long> boardIds) {
        this.boardIds = boardIds;
    }

    @Override
    public String toString() {
        return "ColumnResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tasksIds=" + tasksIds +
                ", boardId=" + boardIds +
                '}';
    }
}
