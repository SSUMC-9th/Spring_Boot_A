package com.example.foodmaster.domain.review.exception;

import com.example.foodmaster.global.apiPayLoad.code.BaseErrorCode;
import com.example.foodmaster.global.apiPayLoad.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}
