package com.example.foodmaster.domain.review.dto;

import com.example.foodmaster.domain.member.entity.Member;
import com.example.foodmaster.domain.store.entity.Store;
import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResDTO {

    @Builder
    public record AddDTO(Long reviewId,
                         String content,
                         Double star,
                         Store store,
                         Member member,
                         LocalDateTime creatAt)
    { }
}
