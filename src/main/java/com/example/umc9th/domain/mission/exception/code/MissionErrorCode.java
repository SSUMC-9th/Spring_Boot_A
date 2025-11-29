package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    // Common Error Codes
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의하세요."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "접근 금지입니다."),

    // Mission Specific Error Codes
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "해당 미션을 찾을 수 없습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_2", "해당 가게를 찾을 수 없습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_3", "사용자 정보를 찾을 수 없습니다."),

    MISSION_ALREADY_CHALLENGED(HttpStatus.CONFLICT, "MISSION409_1", "이미 도전 중인 미션입니다."),
    MISSION_STATUS_NOT_IN_PROGRESS(HttpStatus.BAD_REQUEST, "MISSION400_1", "진행 완료로 변경할 수 없는 상태입니다."), // 상태 변경 실패 시
    INVALID_PAGE_NUMBER(HttpStatus.BAD_REQUEST, "MISSION400_2", "페이지 번호는 1 이상이어야 합니다."), // 커스텀 어노테이션 오류

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}