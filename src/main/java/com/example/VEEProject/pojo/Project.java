package com.example.VEEProject.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Table(name = "project")
public class Project {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @Getter
    @Setter
    Long id;
    @Column(name = "id")
    @Getter
    @Setter
    String name;

    public Project(String projectName) {
    }

    public Project() {

    }


    public Long getProjectId() {
        return id;
    }

    public void setProjectId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return name;
    }

    public void setProjectName(String name) {
        this.name = name;
    }


}