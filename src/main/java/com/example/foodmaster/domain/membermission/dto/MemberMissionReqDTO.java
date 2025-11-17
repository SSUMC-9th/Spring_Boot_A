package com.example.foodmaster.domain.membermission.dto;

import com.example.foodmaster.domain.address.entity.DetailAddress;
import com.example.foodmaster.domain.member.entity.mapping.MemberFood;
import com.example.foodmaster.domain.member.enums.Gender;
import com.example.foodmaster.global.annotation.ExistsFoods;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

// 엔티티 요구

public class MemberMissionReqDTO {

    public record MemberMissionDTO(
            Long memberId,
            Long missionId
    ) {}
}
