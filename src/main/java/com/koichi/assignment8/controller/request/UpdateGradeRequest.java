package com.koichi.assignment8.controller.request;

public class UpdateGradeRequest {

    private Integer newGrade;

    public UpdateGradeRequest(Integer newGrade) {
        this.newGrade = newGrade;
    }

    public Integer getNewGrade() {
        return newGrade;
    }
}
