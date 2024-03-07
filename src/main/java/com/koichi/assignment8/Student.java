package com.koichi.assignment8;

public class Student {
    private int id;
    private String name;

    private String schoolyear;

    private String birthplace;

    public Student(int id, String name, String schoolyear, String birthplace) {
        this.id = id;
        this.name = name;
        this.schoolyear = schoolyear;
        this.birthplace = birthplace;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSchoolyear() {
        return schoolyear;
    }

    public String getBirthplace() {
        return birthplace;
    }
}
