package com.example.foodmaster.domain.review.repository;

import com.example.foodmaster.domain.member.entity.QMember;
import com.example.foodmaster.domain.review.entity.QReview;
import com.example.foodmaster.domain.review.entity.Review;
import com.example.foodmaster.domain.store.entity.QStore;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl{

    private final EntityManager em;

    private final QReview review = QReview.review;
    private final QStore store =  QStore.store;
    private final QMember member = QMember.member;

    @Override
    public List<Review> searchReview(
            Predicate predicate
    ) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory
                .selectFrom(review)
                .join(store).on(review.store.id.eq(store.id))
                .where(predicate)
                .fetch();
    }

    @Override
    public List<Review> searchMyReview(Predicate predicate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory
                .select(review)
                .join(member).on(review.member.id.eq(member.id))
                .join(store).on(review.store.id.eq(store.id))
                .where(predicate)
                .fetch();
    }
}
