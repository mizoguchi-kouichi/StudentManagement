package com.koichi.assignment8.controller.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.koichi.assignment8.ValidGrade;


/**
 * 指定したgradeのstudentをnewGradeに更新するREAD処理で
 * 使用するRequestです。
 */
public class UpdateGradeRequest {

    @ValidGrade
    private Integer newGrade;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UpdateGradeRequest(Integer newGrade) {
        this.newGrade = newGrade;
    }


    public Integer getNewGrade() {
        return newGrade;
    }
}
