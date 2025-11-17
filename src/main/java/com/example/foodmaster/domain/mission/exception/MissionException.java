package com.example.foodmaster.domain.mission.exception;

import com.example.foodmaster.domain.mission.exception.code.MissionErrorCode;
import com.example.foodmaster.global.apiPayLoad.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(MissionErrorCode code) {
        super(code);
    }
}
