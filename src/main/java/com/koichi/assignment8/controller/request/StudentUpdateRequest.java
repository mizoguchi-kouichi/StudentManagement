package com.koichi.assignment8.controller.request;

import com.koichi.assignment8.ValidGrade;
import jakarta.validation.constraints.NotBlank;

/**
 * 指定したidのstudentの name,grade,birthplaceを更新するREAD処理
 * で使用するRequestです。
 */

public class StudentUpdateRequest {

    @NotBlank(message = "nameを入力してください")
    private String name;
    @ValidGrade
    private Integer grade;
    @NotBlank(message = "birthPlaceを入力してください")
    private String birthPlace;

    public StudentUpdateRequest(String name, Integer grade, String birthPlace) {
        this.name = name;
        this.grade = grade;
        this.birthPlace = birthPlace;
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

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }
}
