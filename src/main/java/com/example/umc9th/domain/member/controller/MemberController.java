package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc9th.domain.member.service.command.MemberCommandService;
import com.example.umc9th.domain.member.service.query.MemberQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody @Valid MemberReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.SIGNUP, memberCommandService.signup(dto));
    }


    // 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginDTO> login(
            @RequestBody @Valid MemberReqDTO.LoginDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.LOGIN, memberQueryService.login(dto));
    }

//  // 세션 방식
//    @GetMapping("/members/me")
//    public ApiResponse<String> getMyInfo() {
//        // 세션이 유효하다면 SecurityContextHolder에서 이메일을 가져올 수 있습니다.
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, "현재 로그인 유저: " + email);
//    }


    //Jwt token 방식
    @GetMapping("/members/me")
    public ApiResponse<String> getMyInfo() {
        // JWT 필터가 정상 작동한다면 SecurityContextHolder에서 유저 정보를 가져올 수 있습니다.
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, "현재 로그인 유저: " + email);
    }

    //로그아웃
    @PostMapping("/logout")
    public ApiResponse<String> logout(HttpServletRequest request, HttpServletResponse response) {

        memberQueryService.logout(request, response);
        return ApiResponse.onSuccess(MemberSuccessCode.LOGOUT, "로그아웃 성공");
    }
}