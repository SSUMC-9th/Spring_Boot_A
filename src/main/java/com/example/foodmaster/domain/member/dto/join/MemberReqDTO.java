package com.example.foodmaster.domain.member.dto.join;

import com.example.foodmaster.domain.address.entity.DetailAddress;
import com.example.foodmaster.domain.member.entity.mapping.MemberFood;
import com.example.foodmaster.domain.member.enums.Gender;
import com.example.foodmaster.domain.member.enums.LoginType;
import com.example.foodmaster.global.annotation.ExistsFoods;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            @NotBlank
            String name,
            @Email
            String email,
            @NotBlank
            String password,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotBlank String nickName,
            @NotNull LoginType loginType,
            @NotBlank String phoneNumber
//            @NotNull
//            DetailAddress detailAddress,
//            @ExistsFoods
//            List<MemberFood> preferFoods
    ) {}

    public record MemberMissionDTO(
            Long missionId
            // memberId는 인증된 사용자 정보를 사용해 DTO에 포함하지 않는다.
    ) {}

    // 로그인, 글로벌 auth에도 가능
    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}
