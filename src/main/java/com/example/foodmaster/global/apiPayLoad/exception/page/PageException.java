package com.example.foodmaster.global.apiPayLoad.exception.page;

import com.example.foodmaster.global.apiPayLoad.code.BaseErrorCode;
import com.example.foodmaster.global.apiPayLoad.exception.GeneralException;

public class PageException extends GeneralException {
    public PageException(BaseErrorCode code) {
        super(code);
    }
}
