package com.koichi.assignment8.controller.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public class UpdateGradeRequest {

    private Integer newGrade;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UpdateGradeRequest(Integer newGrade) {
        this.newGrade = newGrade;
    }


    public Integer getNewGrade() {
        return newGrade;
    }
}
