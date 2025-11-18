package com.example.umc9th.domain.review.dto;

import lombok.Getter;
import lombok.Setter; // 요청 DTO는 Setter나 @Data를 사용해 역직렬화가 가능하게 하는 것이 일반적

@Getter
@Setter
public class ReviewRequestDTO  {

    // 외래 키 식별자 (필수: 어떤 가게에 작성하는지)
    private Long storeId;

    // 외래 키 식별자 (필수: 누가 작성하는지)
    private Long memberId;

    // 평점 (score 필드와 매핑)
    private Float score;

    // 내용 (content 필드와 매핑)
    private String content;
}