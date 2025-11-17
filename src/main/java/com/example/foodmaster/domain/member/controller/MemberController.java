package com.example.foodmaster.domain.member.controller;

import com.example.foodmaster.domain.member.dto.MemberReqDTO;
import com.example.foodmaster.domain.member.dto.MemberResDTO;
import com.example.foodmaster.domain.member.exception.code.MemberSuccessCode;
import com.example.foodmaster.domain.member.service.command.MemberCommandService;
import com.example.foodmaster.global.apiPayLoad.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody @Valid MemberReqDTO.JoinDTO dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND,memberCommandService.signUp(dto));
    }
}
