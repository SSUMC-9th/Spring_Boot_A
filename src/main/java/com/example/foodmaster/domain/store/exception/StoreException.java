package com.example.foodmaster.domain.store.exception;

import com.example.foodmaster.global.apiPayLoad.code.BaseErrorCode;
import com.example.foodmaster.global.apiPayLoad.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}
