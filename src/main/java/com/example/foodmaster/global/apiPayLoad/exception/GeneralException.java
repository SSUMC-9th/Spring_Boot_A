package com.example.foodmaster.global.apiPayLoad.exception;

import com.example.foodmaster.global.apiPayLoad.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException{

    private final BaseErrorCode code;
}
