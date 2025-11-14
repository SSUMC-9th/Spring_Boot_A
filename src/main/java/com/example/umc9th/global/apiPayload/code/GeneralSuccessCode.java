package com.example.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    //Success / Error code naming 규칙: 도메인_상태코드_버전

    OK(HttpStatus.OK,
            "COMMON200_1",
            "요청이 성공적으로 처리되었습니다."),

    CREATED(HttpStatus.CREATED,
            "COMMON201_1",
            "리소스가 성공적으로 생성되었습니다."),

    ACCEPTED(HttpStatus.ACCEPTED,
            "COMMON202_1",
            "요청이 접수되었으며 처리 중입니다."),

    NO_CONTENT(HttpStatus.NO_CONTENT,
            "COMMON204_1",
            "성공했으나 응답할 내용이 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
    // private final String detail; // 선택적 상세 설명
}