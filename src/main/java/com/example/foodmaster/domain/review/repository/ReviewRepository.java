package com.example.foodmaster.domain.review.repository;

import com.example.foodmaster.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {

}
