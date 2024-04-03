package com.koichi.assignment8.controller.request;

public class StudentRequest {
    private String name;

    private Integer grade;

    private String birthPlace;

    public StudentRequest(String name, Integer grade, String birthPlace) {
        this.name = name;
        this.grade = grade;
        this.birthPlace = birthPlace;
    }

    public String getName() {
        return name;
    }

    public Integer getGrade() {
        return grade;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

}
