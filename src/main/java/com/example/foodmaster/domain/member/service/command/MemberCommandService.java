package com.example.foodmaster.domain.member.service.command;

import com.example.foodmaster.domain.member.dto.join.MemberReqDTO;
import com.example.foodmaster.domain.member.dto.join.MemberResDTO;

public interface MemberCommandService {

    MemberResDTO.JoinDTO signUp(MemberReqDTO.JoinDTO dto);
}
