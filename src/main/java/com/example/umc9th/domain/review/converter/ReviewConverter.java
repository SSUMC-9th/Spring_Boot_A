package com.example.umc9th.domain.review.converter;

import com.example.umc9th.application.review.dto.ReviewReqDTO;
import com.example.umc9th.application.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.Review;
import com.example.umc9th.domain.review.ReviewPhoto;
import com.example.umc9th.domain.store.Store;
import com.example.umc9th.domain.user.User;

import java.util.stream.Collectors;

public class ReviewConverter {

    // 가게 리뷰용 Review 엔티티 생성
    public static Review toStoreReview(
            ReviewReqDTO.Create dto,
            User user,
            Store store
    ) {
        Review review = Review.builder()
                .user(user)
                .store(store)
                .rating(dto.rating())
                .content(dto.content())
                .build();

        if (dto.photoUrls() != null) {
            for (String url : dto.photoUrls()) {
                ReviewPhoto photo = ReviewPhoto.builder()
                        .review(review)   // 연관관계 주인 설정
                        .url(url)
                        .build();
                review.getPhotos().add(photo); // 양방향 컬렉션 쪽에도 추가
            }
        }

        return review;
    }

    // 응답 DTO
    public static ReviewResDTO.Simple toSimpleDTO(Review review) {
        return ReviewResDTO.Simple.builder()
                .reviewId(review.getId())
                .storeId(review.getStore() != null ? review.getStore().getId() : null)
                .userId(review.getUser().getId())
                .rating(review.getRating())
                .content(review.getContent())
                .photoUrls(
                        review.getPhotos().stream()
                                .map(ReviewPhoto::getUrl)
                                .collect(Collectors.toList())
                )
                .createdAt(review.getCreatedAt())
                .build();
    }
}
