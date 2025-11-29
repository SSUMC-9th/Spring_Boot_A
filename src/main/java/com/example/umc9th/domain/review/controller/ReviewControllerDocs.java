// src/main/java/.../domain/review/controller/ReviewControllerDocs.java

package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; // PostMapping import 추가
import org.springframework.web.bind.annotation.RequestBody;

public interface ReviewControllerDocs {

    // --- 1. 리뷰 목록 조회 (기존) ---
    @Operation(
            summary = "가게의 리뷰 목록 조회 API",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("")
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviews(String storeName, Integer page);


    // --- 2. 리뷰 추가 API (새로 추가) ---
    @Operation(
            summary = "가게에 리뷰 추가 API",
            description = "특정 가게에 리뷰를 작성합니다. (storeId는 경로에서 제외, Body에서 처리)"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "리뷰 생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "가게 또는 사용자를 찾을 수 없음")
    })
    @PostMapping("") // 경로는 동일하게 사용
    public ApiResponse<ReviewResponseDTO> addReview(@RequestBody @Valid ReviewRequestDTO request); // 바디와 유효성 검사 어노테이션만 남김
}