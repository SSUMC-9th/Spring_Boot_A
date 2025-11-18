package com.example.foodmaster.domain.review.service.command;

import com.example.foodmaster.domain.member.repository.MemberRepository;
import com.example.foodmaster.domain.review.converter.ReviewConverter;
import com.example.foodmaster.domain.review.dto.ReviewReqDTO;
import com.example.foodmaster.domain.review.dto.ReviewResDTO;
import com.example.foodmaster.domain.review.entity.Review;
import com.example.foodmaster.domain.review.repository.ReviewRepository;
import com.example.foodmaster.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public ReviewResDTO.AddDTO addReview(
            ReviewReqDTO.AddDTO dto
    ) {
        Review review = ReviewConverter.toReviewEntity(dto);
        reviewRepository.save(review);

        return ReviewConverter.toReviewDTO(review);
    }
}
