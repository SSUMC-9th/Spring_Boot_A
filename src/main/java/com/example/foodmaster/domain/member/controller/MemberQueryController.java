package com.example.foodmaster.domain.member.controller;

import com.example.foodmaster.domain.member.dto.join.MemberReqDTO;
import com.example.foodmaster.domain.member.dto.join.MemberResDTO;
import com.example.foodmaster.domain.member.exception.code.MemberSuccessCode;
import com.example.foodmaster.domain.member.service.command.MemberCommandService;
import com.example.foodmaster.domain.member.service.query.MemberQueryService;
import com.example.foodmaster.global.apiPayLoad.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberQueryController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody @Valid MemberReqDTO.JoinDTO dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND,memberCommandService.signUp(dto));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginDTO> login(
            @RequestBody @Valid MemberReqDTO.LoginDTO dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberQueryService.login(dto));
    }

}
