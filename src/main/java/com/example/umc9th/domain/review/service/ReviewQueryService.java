package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewSearchCondition; // DTO import
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewQueryDsl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.example.umc9th.domain.review.entity.QReview.review;
import static com.example.umc9th.domain.store.entity.QStore.store;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private static final Logger log = LoggerFactory.getLogger(ReviewQueryService.class);
    private final ReviewQueryDsl reviewRepository;

    /**
     * 가게명(storeName)과 별점대(scoreRange)를 이용해 리뷰 목록을 동적으로 조회합니다.
     * @param memberId 현재 로그인 사용자 ID (리뷰는 자신이 작성한 것만 조회)
     * @param condition 가게명, 별점대 필터링 조건
     */
    public List<Review> searchMyReviews(Long memberId, ReviewSearchCondition condition) {

        BooleanBuilder builder = new BooleanBuilder();

        // 1. 필수 조건: 현재 로그인한 사용자의 리뷰만 조회
        builder.and(review.member.id.eq(memberId));

        // 2. 동적 필터링 조건 추가: Null이 아닐 때만 적용

        // 2-1. 가게명 필터링 (String)
        if (condition.getStoreName() != null && !condition.getStoreName().trim().isEmpty()) {
            // containsIgnoreCase를 사용하여 대소문자 무시 및 부분 일치 검색
            builder.and(review.store.storeName.containsIgnoreCase(condition.getStoreName()));
        }

        // 2-2. 별점대 필터링 (Integer to Float Range)
        if (condition.getScoreRange() != null) {
            builder.and(scoreBetween(condition.getScoreRange()));
        }

        log.info("Generated QueryDSL Predicate: {}", builder.toString());

        // 3. Repository 호출 (Paging이 없으므로 List 반환)
        List<Review> reviewList = reviewRepository.searchReview(builder);

        return reviewList;
    }

    /**
     * 별점 정수값(4, 5 등)을 받아 쿼리 조건(4.0 <= score < 5.0)을 생성합니다.
     */
    private BooleanExpression scoreBetween(Integer scoreRange) {
        if (scoreRange == null) return null;

        // 5점대 (5) -> 5.0 <= score <= 5.0 (정확히 5점)
        if (scoreRange == 5) {
            return review.score.eq(5.0f);
        }

        // 4점대 (4) -> 4.0 <= score < 5.0
        Float lowerBound = (float) scoreRange;
        Float upperBound = (float) (scoreRange + 1);

        // score >= lowerBound AND score < upperBound
        return review.score.goe(lowerBound).and(review.score.lt(upperBound));
    }
}