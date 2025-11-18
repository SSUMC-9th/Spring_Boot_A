package com.example.foodmaster.domain.member.dto;

import com.example.foodmaster.domain.address.entity.Address;
import com.example.foodmaster.domain.address.entity.DetailAddress;
import com.example.foodmaster.domain.member.entity.Member;
import com.example.foodmaster.domain.member.entity.mapping.MemberFood;
import com.example.foodmaster.domain.member.enums.Gender;
import com.example.foodmaster.domain.mission.entity.Mission;
import com.example.foodmaster.global.annotation.ExistsFoods;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            @NotBlank
            String name,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            DetailAddress detailAddress,
            @ExistsFoods
            List<MemberFood> preferFoods
    ) {}

    public record MemberMissionDTO(
            Long missionId
            // memberId는 인증된 사용자 정보를 사용해 DTO에 포함하지 않는다.
    ) {}
}
