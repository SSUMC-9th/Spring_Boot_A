package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.umc9th.domain.review.entity.QReview.review;
// QStore가 Review 엔티티의 store 필드 조인을 위해 필요합니다.
import static com.example.umc9th.domain.store.entity.QStore.store;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    // EntityManager 대신 이미 빈으로 등록된 JPAQueryFactory를 주입
    private final JPAQueryFactory queryFactory;

    // 검색 API
    @Override
    public List<Review> searchReview(Predicate predicate) {

        return queryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin() // 성능 최적화를 위한 Fetch Join // 가게명 필터링을 위해 Store 테이블과 조인 명시
                .where(predicate) // Service에서 받은 동적 조건 적용
                .orderBy(review.createdAt.desc()) // 최신순 정렬 추가
                .fetch();
    }
}