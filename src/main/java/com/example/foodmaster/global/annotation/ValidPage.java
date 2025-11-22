package com.example.foodmaster.global.annotation;

import com.example.foodmaster.global.validator.PageRangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PageRangeValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPage {

    // page가 0 이하일 때 보여줄 디폴트 메시지 설정
    String message() default "페이지 번호는 1 이상이어야 합니다.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
