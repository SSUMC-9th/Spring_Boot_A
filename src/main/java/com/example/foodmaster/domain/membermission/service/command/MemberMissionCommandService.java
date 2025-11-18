package com.example.foodmaster.domain.membermission.service.command;

import com.example.foodmaster.domain.membermission.dto.MemberMissionReqDTO;
import com.example.foodmaster.domain.membermission.dto.MemberMissionResDTO;

public interface MemberMissionCommandService {

    MemberMissionResDTO.MemberMissionDTO startMission(MemberMissionReqDTO.MemberMissionDTO dto);
}
