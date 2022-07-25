package com.example.plannerapp.dto.request;

public class TaskRequestDto {
    private String title;
    private String description;
//    private Long columnId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Long getColumnId() {
//        return columnId;
//    }
//
//    public void setColumnId(Long columnId) {
//        this.columnId = columnId;
//    }

    @Override
    public String toString() {
        return "TaskRequestDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
//                ", columnId=" + columnId +
                '}';
    }
}
