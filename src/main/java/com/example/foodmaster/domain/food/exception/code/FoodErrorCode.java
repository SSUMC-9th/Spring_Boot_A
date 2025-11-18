package com.example.foodmaster.domain.food.exception.code;

import com.example.foodmaster.global.apiPayLoad.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FoodErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "FOOD404_1",
            "해당 음식을 찾지 못했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}


//NOT_FOUND(HttpStatus.NOT_FOUND,
//            "MEMBER404_1",
//            "해당 사용자를 찾지 못했습니다."),
//    ;
//
//    private final HttpStatus status;
//    private final String code;
//    private final String message;