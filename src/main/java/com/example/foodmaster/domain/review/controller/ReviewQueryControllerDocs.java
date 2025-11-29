package com.example.foodmaster.domain.review.controller;

import com.example.foodmaster.domain.review.dto.ReviewResDTO;
import com.example.foodmaster.global.annotation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.example.foodmaster.global.apiPayLoad.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
public interface ReviewQueryControllerDocs {

    @Operation(
            summary = "가게의 리뷰 목록 조회 API (개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviewsByStore(
            @RequestParam String storeName,
            @RequestParam Integer page
    );

    @Operation(
            summary = "유저의 리뷰 목록 조회 API (개발 중)",
            description = "특정 유저의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviewsByUser(
            @ValidPage
            @RequestParam(defaultValue = "1") Integer page,
            @PathVariable("memberId") Long memberId
    );
}
