package com.example.umc9th.infrastructure.review;

import com.example.umc9th.domain.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewJpaRepository extends JpaRepository<Review, Long> {

    // 1-1. 특정 가게 리뷰 목록 (사진/답글까지 한 번에 로딩)
    @EntityGraph(attributePaths = {"user", "photos", "replies"})
    Page<Review> findByStore_IdOrderByCreatedAtDesc(Long storeId, Pageable pageable);

    // 1-2. 평균/개수
    @org.springframework.data.jpa.repository.Query("""
      select new com.example.umc9th.application.review.dto.ReviewStatsDto(
        count(r), coalesce(round(avg(r.rating),1), 0)
      )
      from Review r
      where r.store.id = :storeId
    """)
    com.example.umc9th.application.review.dto.ReviewStatsDto findStats(Long storeId);

    @org.springframework.data.jpa.repository.Query("""
  select new com.example.umc9th.application.review.dto.MyReviewItemDto(
    r.id, s.name, r.rating, r.content, r.createdAt, count(rp.id)
  )
  from Review r
    left join r.store s
    left join r.photos rp
  where r.user.id = :userId
  group by r.id, s.name, r.rating, r.content, r.createdAt
  order by r.createdAt desc
""")
    org.springframework.data.domain.Page<
            com.example.umc9th.application.review.dto.MyReviewItemDto
            > findMyReviews(Long userId, org.springframework.data.domain.Pageable pageable);

}

