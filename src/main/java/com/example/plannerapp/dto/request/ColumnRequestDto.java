package com.example.plannerapp.dto.request;

import javax.persistence.UniqueConstraint;

public class ColumnRequestDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ColumnRequestDto{" +
                "name='" + name + '\'' +
//                ", tasksIds=" + tasksIds +
//                ", boardId=" + boardId +
                '}';
    }
}
