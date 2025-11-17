package com.example.umc9th.global.apiPayload;

import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    // 성공한 경우 (result 포함)
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
    }

    // 실패한 경우 (result 포함)
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
    }


    // REVIEW: 이전에 정의된 메서드 시그니처를 유지하기 위해 null 처리
    // 하지만 실제 사용 시에는 BaseSuccessCode를 명시하는 첫 번째 onSuccess를 사용하는 것이 권장됩니다.
    public static ApiResponse<ReviewResponseDTO> onSuccess(ReviewResponseDTO response) {
        // BaseSuccessCode를 인자로 받지 않아 코드를 알 수 없으므로 null 또는 기본값으로 처리합니다.
        // 실제 프로젝트에서는 이 메서드를 사용하지 않는 것이 좋습니다.
        return new ApiResponse<>(true, null, null, response);
    }


    public static ApiResponse<MissionResponseDTO.ChallengeMissionResultDTO> onSuccess(
            MissionResponseDTO.ChallengeMissionResultDTO result
    ) {
        // BaseSuccessCode 인자가 없으므로, 기본적으로 성공 상태(true)와 null 코드를 사용합니다.
        return new ApiResponse<>(true, null, null, result);
    }
}
