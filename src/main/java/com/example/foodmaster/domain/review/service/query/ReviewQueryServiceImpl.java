package com.example.foodmaster.domain.review.service.query;

import com.example.foodmaster.domain.review.converter.ReviewConverter;
import com.example.foodmaster.domain.review.dto.ReviewResDTO;
import com.example.foodmaster.domain.review.entity.Review;
import com.example.foodmaster.domain.review.repository.ReviewRepository;
import com.example.foodmaster.domain.store.entity.Store;
import com.example.foodmaster.domain.store.exception.StoreException;
import com.example.foodmaster.domain.store.exception.code.StoreErrorCode;
import com.example.foodmaster.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(
            String storeName, Integer page
    ) {
        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        return ReviewConverter.toReviewPreviewListDTO(result);
    }
}
