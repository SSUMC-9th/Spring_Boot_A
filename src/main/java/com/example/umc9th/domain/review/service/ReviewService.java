package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // 리뷰 데이터를 받아 데이터베이스에 저장(INSERT)하는 로직
    public Review createReview(ReviewRequestDTO reviewDto) {

        // 1. DTO를 엔티티로 변환 (엔티티 빌더 사용)
        Review newReview = Review.builder()
                .store(Store.builder().build())
                .member(Member.builder().build())
                .score(reviewDto.getScore())
                .content(reviewDto.getContent())
                .build();

        // 2. save() 메서드 호출: JPA가 INSERT 쿼리를 자동 생성 및 실행
        return reviewRepository.save(newReview);
    }
}