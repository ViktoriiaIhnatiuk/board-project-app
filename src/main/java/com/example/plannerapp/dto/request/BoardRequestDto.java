package com.example.plannerapp.dto.request;

public class BoardRequestDto {
    private String name;
//    private String backgroundImagePath;

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


    @Override
    public String toString() {
        return "BoardRequestDto{" +
                "name='" + name + '\'' +
//                ", backgroundImagePath='" + backgroundImagePath + '\'' +
                '}';
    }
}
