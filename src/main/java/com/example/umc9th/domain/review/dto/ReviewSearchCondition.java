package com.example.umc9th.domain.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewSearchCondition {
    private String storeName;  // 필터링 조건 1: 가게명 (예: "반이학생마라탕")
    private Integer scoreRange; // 필터링 조건 2: 별점대 (예: 5, 4, 3 등 정수)
}