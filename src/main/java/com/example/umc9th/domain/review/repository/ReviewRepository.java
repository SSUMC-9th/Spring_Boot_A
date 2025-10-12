package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

// Review 엔티티와 PK 타입(Long)을 상속받아 기본 CRUD 기능을 제공받습니다.
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 여기에 추가적인 쿼리 작성 없이 save() 메서드를 바로 사용할 수 있습니다.
}