package com.example.boardprojectapp.dto.request;

import java.util.List;

public class ColumnRequestDto {
    private String name;
    private List<Long> tasksIds;
    private Long boardId;

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
        return "ColumnRequestDto{" +
                "name='" + name + '\'' +
                ", tasksIds=" + tasksIds +
                ", boardId=" + boardId +
                '}';
    }
}
