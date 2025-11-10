package com.example.foodmaster.domain.review.service;

import com.example.foodmaster.domain.member.entity.QMember;
import com.example.foodmaster.domain.review.entity.QReview;
import com.example.foodmaster.domain.review.entity.Review;
import com.example.foodmaster.domain.review.repository.ReviewRepository;
import com.example.foodmaster.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> searchReview(String query, String type) {

        // Q클래스 정의
        QReview review = QReview.review;
        QStore store = QStore.store;

        // BooleanBuilder 정의 및 사용
        BooleanBuilder builder = new BooleanBuilder();

        // 동적 쿼리: 검색 조건
        if (type.equals("location")) {
            builder.and(store.detailAddress.detailAddressLine.contains(query));
        }
        if (type.equals("star")) {
            builder.and(review.star.goe(Double.parseDouble(query)));
        }
        if (type.equals("both")) {

            // & 기준 변환
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            builder.and(store.detailAddress.detailAddressLine.contains(firstQuery));
            builder.and(review.star.goe(Double.parseDouble(secondQuery)));
        }

        List<Review> reviewList = reviewRepository.searchReview(builder);

        return reviewList;
    }

    public List<Review> searchMyReview(
            Long memberId,
            Long storeId,
            Double star
    ) {
        QReview review = QReview.review;
        QStore store = QStore.store;
        QMember member = QMember.member;

        BooleanBuilder builder = new BooleanBuilder();

        if (memberId != null) {
            builder.and(member.id.eq(memberId));
        }

        if (storeId != null) {
            builder.and(store.id.eq(storeId));
        }

        if (star != null) {
            builder.and(review.star.goe(star));
            builder.and(review.star.lt(star + 1.0));
        }

        List<Review> reviewList = reviewRepository.searchMyReview(builder);
        return reviewList;
    }
}
