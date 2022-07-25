package com.example.plannerapp.dto.request;

public class BoardRequestDto {
    private String name;
//    private String backgroundImagePath;
//    private List<Long> columnsIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    public String getBackgroundImagePath() {
//        return backgroundImagePath;
//    }
//
//    public void setBackgroundImagePath(String backgroundImagePath) {
//        this.backgroundImagePath = backgroundImagePath;
//    }
//
//    public List<Long> getColumnsIds() {
//        return columnsIds;
//    }
//
//    public void setColumnsIds(List<Long> columnsIds) {
//        this.columnsIds = columnsIds;
//    }

    @Override
    public String toString() {
        return "BoardRequestDto{" +
                "name='" + name + '\'' +
//                ", backgroundImagePath='" + backgroundImagePath + '\'' +
//                ", columnsIds=" + columnsIds +
                '}';
    }
}
