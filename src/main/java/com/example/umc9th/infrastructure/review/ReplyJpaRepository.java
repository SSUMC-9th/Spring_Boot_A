package com.example.umc9th.infrastructure.review;

import com.example.umc9th.domain.review.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyJpaRepository extends JpaRepository<Reply, Long> {}
