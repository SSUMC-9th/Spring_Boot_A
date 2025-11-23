package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

// Review 엔티티와 PK 타입(Long)을 상속받아 기본 CRUD 기능을 제공받습니다.
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {


    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
}