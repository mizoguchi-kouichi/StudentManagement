package com.koichi.assignment8.controller.request;

import com.koichi.assignment8.ValidGrade;
import jakarta.validation.constraints.NotBlank;

/**
 * INSERTの際に使用するRequestです。
 */
public class StudentPostRequest {
    @NotBlank(message = "nameを入力してください")
    private String name;
    @ValidGrade
    private Integer grade;
    @NotBlank(message = "birthPlaceを入力してください")
    private String birthPlace;

    public StudentPostRequest(String name, Integer grade, String birthPlace) {
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
