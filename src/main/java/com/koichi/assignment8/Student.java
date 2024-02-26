package com.koichi.assignment8;

public class Student {
    private int id;
    private String name;

    private String birthplace;

    public Student(int id, String name, String birthplace) {
        this.id = id;
        this.name = name;
        this.birthplace = birthplace;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthplace() {
        return birthplace;
    }
}
