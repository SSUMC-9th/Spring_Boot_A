package com.example.umc9th.application.mission.service;


import com.example.umc9th.application.mission.dto.UserMissionResDTO;

public interface UserMissionCommandService {

    UserMissionResDTO.Challenge challengeMission(Long missionId);
}
