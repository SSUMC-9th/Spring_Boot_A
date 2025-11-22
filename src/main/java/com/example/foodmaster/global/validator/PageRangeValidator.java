package com.example.foodmaster.global.validator;

import com.example.foodmaster.global.annotation.ValidPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PageRangeValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        return page >= 1;
    }
}
