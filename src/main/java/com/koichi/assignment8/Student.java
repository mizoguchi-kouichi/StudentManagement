package com.koichi.assignment8;

public class Student {
    private int id;
    private String name;

    private String schoolYear;

    private String birthPlace;


    public Student(int id, String name, String schoolYear, String birthPlace) {
        this.id = id;
        this.name = name;
        this.schoolYear = schoolYear;
        this.birthPlace = birthPlace;
    }

    public int getId() {
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
