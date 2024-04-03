package com.koichi.assignment8.entity;

public class Student {
    private Integer id;
    private String name;

    private Integer grade;

    private String birthPlace;

    public Student(Integer id, String name, Integer grade, String birthPlace) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.birthPlace = birthPlace;
    }

    public Student(String name, Integer grade, String birthPlace) {
        this.id = null;
        this.name = name;
        this.grade = grade;
        this.birthPlace = birthPlace;
    }

    public Integer getId() {
        return id;
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