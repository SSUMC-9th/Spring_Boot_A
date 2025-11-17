package com.example.foodmaster.global.validator;

import com.example.foodmaster.domain.food.exception.code.FoodErrorCode;
import com.example.foodmaster.domain.food.repository.FoodRepository;
import com.example.foodmaster.global.annotation.ExistsFoods;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FoodExistValidator implements ConstraintValidator<ExistsFoods, List<Long>> {

    private final FoodRepository foodRepository;

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        // 1. 모든 ID가 DB에 존재하는지 확인
        boolean isValid = values.stream()
                .allMatch(value -> foodRepository.existsById(value));

        // 2. 유효성 검사 실패 시
        if (!isValid) {
            // 기존의 기본 에러 메시지 비활성화
            context.disableDefaultConstraintViolation();

            // 커스텀 에러 메시지를 설정하고 유효성 위반을 추가
            context.buildConstraintViolationWithTemplate(FoodErrorCode.NOT_FOUND.getMessage())
                    .addConstraintViolation();
        }

        // 3. 검증 결과 반환
        return isValid; // isValid 변수가 boolean 타입이므로 반드시 반환되어야 합니다.
    }
}
