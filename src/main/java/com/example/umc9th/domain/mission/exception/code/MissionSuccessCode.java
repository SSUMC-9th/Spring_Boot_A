package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    // Common Success Codes
    _OK(HttpStatus.OK, "COMMON200", "요청에 성공했습니다."),

    // Mission Specific Success Codes
    MISSION_CHALLENGE_SUCCESS(HttpStatus.CREATED, "MISSION201", "미션 도전이 성공적으로 시작되었습니다."),
    MISSION_LIST_FOUND(HttpStatus.OK, "MISSION200_1", "미션 목록 조회에 성공했습니다."),
    MISSION_COMPLETE_UPDATED(HttpStatus.OK, "MISSION200_2", "미션 상태가 '진행 완료'로 변경되었습니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}