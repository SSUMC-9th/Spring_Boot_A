package com.example.foodmaster.global.apiPayLoad.code;

import org.springframework.http.HttpStatus;


public interface BaseErrorCode {

    HttpStatus getStatus();
    String getCode();
    String getMessage();

}
