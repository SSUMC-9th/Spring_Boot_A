package com.example.umc9th.infrastructure.review;

import com.example.umc9th.domain.review.Review;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewQueryDsl {
    // 단순 컬렉션 반환
    List<Review> searchReview(Predicate predicate);

    Page<Review> searchReview(Predicate predicate, Pageable pageable);
}

