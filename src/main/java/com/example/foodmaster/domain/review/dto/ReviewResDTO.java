package com.example.foodmaster.domain.review.dto;

import com.example.foodmaster.domain.member.entity.Member;
import com.example.foodmaster.domain.store.entity.Store;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record AddDTO(Long reviewId,
                         String content,
                         Double star,
                         Store store,
                         Member member,
                         LocalDateTime creatAt)
    { }

    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElement,
            Boolean isFirst,
            Boolean isLast
    )
    {}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Double star,
            String content,
            LocalDate createdAt
    ) {}
}
