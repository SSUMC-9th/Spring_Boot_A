package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;
import java.util.List;

public interface ReviewQueryDsl {

    // 검색 API: Predicate (BooleanBuilder)를 받아 쿼리를 실행합니다.
    List<Review> searchReview(Predicate predicate);
}