package com.example.foodmaster.domain.member.service.query;

import com.example.foodmaster.domain.member.dto.join.MemberReqDTO;
import com.example.foodmaster.domain.member.dto.join.MemberResDTO;
import jakarta.validation.Valid;

public interface MemberQueryService {
    MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO dto);
}
