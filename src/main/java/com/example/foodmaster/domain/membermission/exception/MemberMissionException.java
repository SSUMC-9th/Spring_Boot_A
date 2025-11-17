package com.example.foodmaster.domain.membermission.exception;

import com.example.foodmaster.domain.membermission.exception.code.MemberMissionErrorCode;
import com.example.foodmaster.global.apiPayLoad.code.BaseErrorCode;
import com.example.foodmaster.global.apiPayLoad.exception.GeneralException;

public class MemberMissionException extends GeneralException {
    public MemberMissionException(BaseErrorCode code) {
        super(code);
    }
}
