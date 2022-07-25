package com.example.plannerapp.dto.response;

import java.util.List;

public class ColumnResponseDtoWithTasks {
    private Long id;
    private String name;
    private List<TaskResponseDto> tasks;
    private List <Long> boardIds;

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

    public List<TaskResponseDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskResponseDto> tasks) {
        this.tasks = tasks;
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
                ", tasks=" + tasks +
                ", boardId=" + boardIds +
                '}';
    }
}
