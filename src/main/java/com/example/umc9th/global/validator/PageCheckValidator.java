package com.example.umc9th.global.validator;


import com.example.umc9th.global.annotation.CheckPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageCheckValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        if (page == null) {
            return false; // 페이지가 null인 경우 유효하지 않음
        }
        // 페이지 범위가 너무 작은지 (0 이하) 판단 -> 1 이상이어야 유효
        return page >= 1;
    }
}