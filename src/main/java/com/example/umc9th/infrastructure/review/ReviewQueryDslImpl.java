// src/main/java/com/example/umc9th/infrastructure/review/ReviewQueryDslImpl.java
package com.example.umc9th.infrastructure.review;

import com.example.umc9th.domain.review.QReview;
import com.example.umc9th.domain.review.Review;
import com.example.umc9th.domain.store.QStore;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> searchReview(Predicate predicate) {
        QReview review = QReview.review;
        QStore store   = QStore.store;

        return queryFactory
                .selectFrom(review)
                .leftJoin(review.store, store).fetchJoin()
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .fetch();
    }

    @Override
    public Page<Review> searchReview(Predicate predicate, Pageable pageable) {
        QReview review = QReview.review;
        QStore store   = QStore.store;

        List<Review> content = queryFactory
                .selectFrom(review)
                .leftJoin(review.store, store).fetchJoin()
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(review.id.count())
                .from(review)
                .leftJoin(review.store, store)
                .where(predicate)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}

