package com.example.umc9th.domain.member.service.query;

import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MemberQueryService {
    MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO dto);

    void logout(HttpServletRequest request, HttpServletResponse response);
}
