package com.example.plannerapp.dto.response;

public class DeletedSectionResponseDto {
    private Long id;
    private String name;

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

    @Override
    public String toString() {
        return "DeletedSectionResponseDto{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
