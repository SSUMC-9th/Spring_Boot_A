package com.example.foodmaster.domain.review.dto;

import com.example.foodmaster.domain.member.entity.Member;
import com.example.foodmaster.domain.store.entity.Store;

public class ReviewReqDTO {

    public record AddDTO(
            String content,
            Double star,
            Member member,
            Store store
    ) {}
}
