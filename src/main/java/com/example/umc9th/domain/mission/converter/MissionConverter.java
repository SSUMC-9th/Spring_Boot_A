package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.UserMission;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.stream.Collectors;
public class MissionConverter {

    // Mission 및 Member 엔티티를 UserMission 엔티티로 변환
    public static UserMission toUserMission(Mission mission, Member member) {
        return UserMission.builder()
                .mission(mission)
                .member(member)
                .status(MissionStatus.IN_PROGRESS) // 미션 도전 시 '도전 중' 상태로 고정
                // createdAt은 BaseEntity 상속을 통해 자동 생성된다고 가정
                .build();
    }

    // UserMission 엔티티를 응답 DTO로 변환
    public static MissionResponseDTO.ChallengeMissionResultDTO toChallengeMissionResultDTO(
            UserMission userMission
    ) {
        return MissionResponseDTO.ChallengeMissionResultDTO.builder()
                .userMissionId(userMission.getId())
                .challengedAt(userMission.getCreatedAt())
                .build();
    }


    public static MissionResponseDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        // 무조건 빌더 패턴 사용
        return MissionResponseDTO.MissionPreviewDTO.builder()
                .missionId(mission.getId())
                .point(mission.getPoint())
                .storeName(mission.getStore().getStoreName()) // Store 엔티티에서 가게명 추출
                .description(mission.getDescription())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResponseDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<Mission> missions) {

        List<MissionResponseDTO.MissionPreviewDTO> missionList = missions.stream()
                .map(MissionConverter::toMissionPreviewDTO) // 각 Mission 엔티티를 DTO로 변환
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionPreviewListDTO.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                // 프론트엔드가 1 이상의 page 번호를 전달하므로, 0부터 시작하는 DB 페이지 번호에 1을 더합니다.
                .currentPage(missions.getNumber() + 1)
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .build();
    }


    public static MissionResponseDTO.MyMissionPreviewDTO toMyMissionPreviewDTO(UserMission memberMission) {
        // 빌더 패턴 사용
        return MissionResponseDTO.MyMissionPreviewDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getStoreName())
                .description(memberMission.getMission().getDescription())
                .deadline(memberMission.getMission().getDeadline())
                .challengedAt(memberMission.getCreatedAt()) // 도전 시작일
                .status(memberMission.getStatus())
                .build();
    }

    public static MissionResponseDTO.MyMissionListDTO toMyMissionListDTO(Page<UserMission> memberMissions) {

        // Converter에서 for문을 사용해서는 안되며, 무조건 Java의 Stream을 사용해야 한다.
        List<MissionResponseDTO.MyMissionPreviewDTO> missionList = memberMissions.stream()
                .map(MissionConverter::toMyMissionPreviewDTO)
                .collect(Collectors.toList());

        // 빌더 패턴 사용
        return MissionResponseDTO.MyMissionListDTO.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(memberMissions.getTotalPages())
                .totalElements(memberMissions.getTotalElements())
                .currentPage(memberMissions.getNumber() + 1) // 프론트엔드 page 1부터 시작
                .isFirst(memberMissions.isFirst())
                .isLast(memberMissions.isLast())
                .build();
    }


    public static MissionResponseDTO.MissionCompleteResultDTO toMissionCompleteResultDTO(UserMission userMission) {
        Mission mission = userMission.getMission();

        // 무조건 빌더 패턴 사용
        return MissionResponseDTO.MissionCompleteResultDTO.builder()
                .userMissionId(userMission.getId())
                .storeName(mission.getStore().getStoreName())
                .description(mission.getDescription())
                .updatedStatus(userMission.getStatus())
                .completedAt(userMission.getUpdatedAt()) // BaseEntity의 updatedAt을 완료 시점으로 사용 가정
                .earnedPoint(mission.getPoint())
                .build();
    }
}