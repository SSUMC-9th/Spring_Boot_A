package com.example.umc9th.infrastructure.review;

import com.example.umc9th.domain.review.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewPhotoJpaRepository extends JpaRepository<ReviewPhoto, Long> {}
