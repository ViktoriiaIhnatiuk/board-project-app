package com.example.plannerapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "boards")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String backgroundImagePath;
    @ManyToMany (cascade = {CascadeType.REMOVE})
    private List<Collumn> collumns;

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

    public String getBackgroundImagePath() {
        return backgroundImagePath;
    }

    public void setBackgroundImagePath(String backgroundImagePath) {
        this.backgroundImagePath = backgroundImagePath;
    }

    public List<Collumn> getColumns() {
        return collumns;
    }

    public void setColumns(List<Collumn> collumns) {
        this.collumns = collumns;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", backgroundImagePath='" + backgroundImagePath + '\'' +
                ", collumns=" + collumns +
                '}';
    }
}
