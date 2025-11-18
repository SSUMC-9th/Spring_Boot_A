package com.example.foodmaster.domain.member.service.command;

import com.example.foodmaster.domain.member.dto.MemberReqDTO;
import com.example.foodmaster.domain.member.dto.MemberResDTO;

public interface MemberCommandService {

    MemberResDTO.JoinDTO signUp(MemberReqDTO.JoinDTO dto);
}
