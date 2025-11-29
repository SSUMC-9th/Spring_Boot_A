package com.example.foodmaster.global.apiPayLoad.exception.page.code;

import com.example.foodmaster.global.apiPayLoad.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PageErrorCode implements BaseErrorCode {

    PAGE_NOT_VALID(HttpStatus.BAD_REQUEST,
            "Page404_1",
            "페이지는 0 이하일 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

