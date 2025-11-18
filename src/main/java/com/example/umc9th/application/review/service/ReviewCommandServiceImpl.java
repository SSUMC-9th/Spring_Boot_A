package com.example.umc9th.application.review.service;

import com.example.umc9th.application.review.dto.ReviewReqDTO;
import com.example.umc9th.application.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.Review;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.store.Store;
import com.example.umc9th.domain.user.User;
import com.example.umc9th.infrastructure.review.ReviewJpaRepository;
import com.example.umc9th.infrastructure.store.StoreJpaRepository;
import com.example.umc9th.infrastructure.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewJpaRepository reviewRepository;
    private final StoreJpaRepository storeRepository;
    private final UserJpaRepository userRepository;

    // ★ 로그인 대신 하드코딩 유저 ID
    private static final Long MOCK_USER_ID = 1L;

    @Override
    public ReviewResDTO.Simple createStoreReview(Long storeId, ReviewReqDTO.Create dto) {

        // 1. 유저 하드코딩 조회
        User user = userRepository.findById(MOCK_USER_ID)
                .orElseThrow(() -> new IllegalStateException("MOCK_USER_ID 유저가 없습니다."));

        // 2. 가게 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다. id=" + storeId));

        // 3. DTO → 엔티티
        Review review = ReviewConverter.toStoreReview(dto, user, store);

        // 4. 저장
        reviewRepository.save(review);

        // 5. 엔티티 → 응답 DTO
        return ReviewConverter.toSimpleDTO(review);
    }
}
