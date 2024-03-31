package com.koichi.assignment8;

public class Student {
    private Integer id;
    private String name;

    private String schoolYear;

    private String birthPlace;

    public Student(Integer id, String name, String schoolYear, String birthPlace) {
        this.id = id;
        this.name = name;
        this.schoolYear = schoolYear;
        this.birthPlace = birthPlace;
    }

    public Student(String name, String schoolYear, String birthPlace) {
        this.id = null;
        this.name = name;
        this.schoolYear = schoolYear;
        this.birthPlace = birthPlace;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public String getBirthPlace() {
        return birthPlace;
    }
}
