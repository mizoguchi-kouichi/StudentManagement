package com.koichi.assignment8.controller.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.util.Optional;


/**
 * 指定したgradeのstudentをnewGradeに更新するREAD処理で
 * 使用するRequestです。
 */
public class UpdateGradeRequest {

    @ValidGrade
    private String newGrade;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UpdateGradeRequest(String newGrade) {
        this.newGrade = newGrade;
    }

    public String getNewGrade() {
        return newGrade;
    }

    public enum Grade {
        一年生,
        二年生,
        三年生,
        卒業生;

        public static UpdateGradeRequest.Grade from(String value) {
            return Optional.of(UpdateGradeRequest.Grade.valueOf(value.toUpperCase())).orElseThrow(() -> new IllegalArgumentException("有効な学年を指定してください（二年生, 三年生,卒業生のいずれか）。"));
        }
    }

    /**
     * 学年のバリデーション用アノテーション
     */
    @Documented
    @Constraint(validatedBy = UpdateGradeRequest.GradeValidator.class)
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ValidGrade {
        String message() default "有効な学年を指定してください（二年生, 三年生,卒業生のいずれか）。";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    /**
     * 学年のバリデータ
     */
    public static class GradeValidator implements ConstraintValidator<UpdateGradeRequest.ValidGrade, String> {

        @Override
        public void initialize(UpdateGradeRequest.ValidGrade constraintAnnotation) {
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null) {
                return true; // nullの場合はバリデーションしない
            }

            try {
                UpdateGradeRequest.Grade.from(value);
                return true;
            } catch (IllegalArgumentException e) { // 有効な学年でない場合はバリデーションエラー
                return false;
            }
        }
    }
}
