package com.example.foodmaster.domain.review.exception.code;

import com.example.foodmaster.global.apiPayLoad.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "MEMBER200_1",
            "성공적으로 리뷰를 찾았습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
