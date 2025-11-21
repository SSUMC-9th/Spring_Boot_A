package com.example.foodmaster.domain.store.exception.code;

import com.example.foodmaster.global.apiPayLoad.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    ADD_COMPLETE(HttpStatus.OK,
            "STORE_1",
            "성공적으로 가게를 찾았습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
