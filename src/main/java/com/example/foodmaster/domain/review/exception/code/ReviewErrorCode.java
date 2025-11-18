package com.example.foodmaster.domain.review.exception.code;

import com.example.foodmaster.global.apiPayLoad.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "해당 가게를 찾지 못했습니다."
            )
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
