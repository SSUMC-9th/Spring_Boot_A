package com.example.foodmaster.domain.mission.exception.code;

import com.example.foodmaster.global.apiPayLoad.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 미션을 찾았습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
