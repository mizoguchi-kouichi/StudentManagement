package com.koichi.assignment8.entity;

import jakarta.validation.constraints.NotBlank;

public class Student {

    private Integer id;

    @NotBlank(message = "nameを入力してください")
    private String name;

    private String grade;

    @NotBlank(message = "birthPlaceを入力してください")
    private String birthPlace;

    private String newGrade;

    public Student(Integer id, String name, String grade, String birthPlace) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.birthPlace = birthPlace;
    }

    public Student(String name, String grade, String birthPlace) {
        this.id = null;
        this.name = name;
        this.grade = grade;
        this.birthPlace = birthPlace;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }


    public void setNewGrade(String newGrade) {
        this.newGrade = newGrade;
    }


}
