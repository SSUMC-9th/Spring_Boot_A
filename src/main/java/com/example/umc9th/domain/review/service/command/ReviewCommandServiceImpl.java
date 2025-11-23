package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public ReviewResponseDTO addReview(ReviewRequestDTO request) {

        // 1. ✨ 하드 코딩된 Member 찾기 (memberId 필드는 무시)
        // 실제로는 request.getMemberId()를 사용하지만, 하드 코딩 요구사항에 따라 1L 사용
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("하드 코딩된 유저(ID: 1)를 찾을 수 없습니다."));

        // 2. Store 찾기
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));

        // 3. DTO -> Entity 변환 및 저장
        Review newReview = ReviewConverter.toReview(request, store, member);
        Review savedReview = reviewRepository.save(newReview);

        // 4. Entity -> Response DTO 변환 및 반환
        return ReviewConverter.toReviewResponseDTO(savedReview);
    }
}