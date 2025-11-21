package com.example.foodmaster.domain.review.repository;

import com.example.foodmaster.domain.review.entity.Review;
import com.example.foodmaster.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {

    Page<Review> findAllByStore(Store store, Pageable pageable);
}
