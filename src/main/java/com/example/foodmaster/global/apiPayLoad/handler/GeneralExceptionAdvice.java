package com.example.foodmaster.global.apiPayLoad.handler;

import com.example.foodmaster.global.apiPayLoad.ApiResponse;
import com.example.foodmaster.global.apiPayLoad.code.GeneralErrorCode;
import com.example.foodmaster.global.apiPayLoad.exception.GeneralException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handlerException(
            GeneralException ex
    ) {
        return ResponseEntity.status(ex.getCode().getStatus())
                .body(ApiResponse.onFailure(
                        ex.getCode(),
                        null
                    )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handlerException (
            Exception ex
    ) {
        GeneralErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(
                        code,
                        ex.getMessage()
                ));
    }

    // 컨트롤러 메서드에서 @Valid 어노테이션을 사용하여 DTO의 유효성 검사를 수행
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ) {
        // 검사에 실패한 필드와 그에 대한 메시지를 저장하는 Map
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        GeneralErrorCode code = GeneralErrorCode.VALID_FAIL;
        ApiResponse<Map<String, String>> errorResponse = ApiResponse.onFailure(code, errors);

        // 에러 코드, 메시지와 함께 errors를 반환
        return ResponseEntity.status(code.getStatus()).body(errorResponse);
    }

    // @RequestParam 등 단일 인자 검증 실패 시 처리
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ApiResponse<Map<String, String>>> handleConstraintViolationException(
            ConstraintViolationException ex
    ) {
        // 검사에 실패한 인자와 그에 대한 메시지를 저장하는 Map
        Map<String, String> errors = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(), // 실패한 속성 경로
                        ConstraintViolation::getMessage, // @ValidPage에 정의된 메시지 ("페이지 번호는 1 이상이어야 합니다.")
                        (existing, replacement) -> existing, // 키 충돌 시 기존 값 유지
                        HashMap::new // Map 생성
                ));

        GeneralErrorCode code = GeneralErrorCode.VALID_FAIL; // DTO 검증 실패와 동일한 코드 사용
        ApiResponse<Map<String, String>> errorResponse = ApiResponse.onFailure(code, errors);

        // 에러 코드, 메시지와 함께 errors를 반환 (400 Bad Request)
        return ResponseEntity.status(code.getStatus()).body(errorResponse);
    }
}
