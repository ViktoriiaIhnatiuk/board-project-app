package com.example.plannerapp.dto.request;

public class SectionRequestDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SectionRequestDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
