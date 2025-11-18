package com.example.foodmaster.domain.membermission.exception.code;

import com.example.foodmaster.global.apiPayLoad.code.BaseErrorCode;
import com.example.foodmaster.global.apiPayLoad.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionSuccessCode implements BaseSuccessCode {

    ADD_COMPLETE(HttpStatus.OK,
            "MEMBER_MISSION200_1",
            "성공적으로 미션을 추가했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
