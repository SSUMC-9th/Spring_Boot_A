package com.example.foodmaster.domain.member.dto;

import com.example.foodmaster.domain.member.entity.Member;
import com.example.foodmaster.domain.mission.entity.Mission;

public class HomeDTO {

    private final Member member;
    private final Mission mission;

    public HomeDTO(Member member, Mission mission) {
        this.member = member;
        this.mission = mission;
    }
}
