package com.example.umc9th.domain.member.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "MEMBER200_1",
            "성공적으로 사용자를 조회했습니다."),
    LOGIN(HttpStatus.OK,
            "MEMBER200_2",
            "로그인성공."),
    LOGOUT(HttpStatus.OK,
            "MEMBER200_3",
            "로그아웃 성공."),
    SIGNUP(HttpStatus.OK,
            "MEMBER200_4",
            "회원가입 성공."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}