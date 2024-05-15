package com.koichi.assignment8.controller.request;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.*;
import java.util.Optional;

/**
 * 指定したidのstudentの name,grade,birthplaceを更新するREAD処理
 * で使用するRequestです。
 */

public class StudentUpdateRequest {

    @NotBlank(message = "nameを入力してください")
    private String name;
    @ValidGrade
    private String grade;
    @NotBlank(message = "birthPlaceを入力してください")
    private String birthPlace;

    public StudentUpdateRequest(String name, String grade, String birthPlace) {
        this.name = name;
        this.grade = grade;
        this.birthPlace = birthPlace;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public enum Grade {
        一年生,
        二年生,
        三年生,
        卒業生;

        public static StudentUpdateRequest.Grade from(String value) {
            return Optional.of(StudentUpdateRequest.Grade.valueOf(value.toUpperCase())).orElseThrow(() -> new IllegalArgumentException("有効な学年を指定してください（一年生, 二年生, 三年生,卒業生のいずれか）。"));
        }
    }

    /**
     * 学年のバリデーション用アノテーション
     */
    @Documented
    @Constraint(validatedBy = StudentUpdateRequest.GradeValidator.class)
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ValidGrade {
        String message() default "有効な学年を指定してください（一年生, 二年生, 三年生,卒業生のいずれか）。";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    /**
     * 学年のバリデータ
     */
    public static class GradeValidator implements ConstraintValidator<StudentUpdateRequest.ValidGrade, String> {

        @Override
        public void initialize(StudentUpdateRequest.ValidGrade constraintAnnotation) {
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null) {
                return true; // nullの場合はバリデーションしない
            }

            try {
                StudentUpdateRequest.Grade.from(value);
                return true;
            } catch (IllegalArgumentException e) { // 有効な学年でない場合はバリデーションエラー
                return false;
            }
        }
    }
}

