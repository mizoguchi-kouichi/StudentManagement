package com.koichi.assignment8;

public class StudentRequest {
    private String name;

    private String schoolYear;

    private String birthPlace;

    public StudentRequest(String name, String schoolYear, String birthPlace) {
        this.name = name;
        this.schoolYear = schoolYear;
        this.birthPlace = birthPlace;
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
