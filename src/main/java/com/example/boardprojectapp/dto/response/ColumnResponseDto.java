package com.example.boardprojectapp.dto.response;

import java.util.List;

public class ColumnResponseDto {
    private Long id;
    private String name;
    private List<Long> tasksIds;
    private Long boardId;

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

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    @Override
    public String toString() {
        return "ColumnResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tasksIds=" + tasksIds +
                ", boardId=" + boardId +
                '}';
    }
}
