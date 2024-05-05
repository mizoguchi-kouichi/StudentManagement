package com.koichi.assignment8.controller.request;

import com.koichi.assignment8.ValidGrade;
import jakarta.validation.constraints.NotBlank;

public class StudentRequest {
    @NotBlank(message = "nameを入力してください")
    private String name;
    @ValidGrade
    private Integer grade;
    @NotBlank(message = "birthPlaceを入力してください")
    private String birthPlace;

    private Integer newGrade;

    public StudentRequest(String name, Integer grade, String birthPlace, Integer newGrade) {
        this.name = name;
        this.grade = grade;
        this.birthPlace = birthPlace;
        this.newGrade = newGrade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade, Integer newGrade) {
        this.grade = grade;
        this.newGrade = newGrade;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Integer getNewGrade() {
        return newGrade;
    }

    public void setNewGrade(Integer newGrade) {
        this.newGrade = newGrade;
    }
}
