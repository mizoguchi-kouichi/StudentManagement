package com.koichi.assignment8;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@NotNull
@Max(value = 3)
@Min(value = 1)
public @interface ValidGrade {

    String message() default "gradeは1～3の間を入力してください";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
