package com.example.umc9th.global.apiPayload.code; // BaseErrorCode와 동일한 패키지 사용

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum FoodErrorCode implements BaseErrorCode {

    // FoodExistValidator에서 사용하려고 했던 에러 코드
    // 404 NOT_FOUND 에러 정의
    FOOD_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD4001", "해당하는 음식 카테고리를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    FoodErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return httpStatus;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}