package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.UserMission;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.repository.UserMissionRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final UserMissionRepository userMissionRepository;

    // ====================================================================
    // 1. 특정 가게의 미션 목록 조회
    // ====================================================================
    public MissionResponseDTO.MissionPreviewListDTO getMissionListByStore(Long storeId, int page) {

        // 1. Store 존재 여부 검증
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // 2. Pageable 객체 생성 (page - 1 처리)
        Pageable pageable = PageRequest.of(page - 1, 10);

        // 3. Repository 호출 및 조회
        Page<Mission> missions = missionRepository.findAllByStore(store, pageable);

        // 4. Converter를 이용해 DTO로 변환
        return MissionConverter.toMissionPreviewListDTO(missions);
    }

    // ====================================================================
    // 2. 내가 진행중인 미션 목록 조회
    // ====================================================================
    public MissionResponseDTO.MyMissionListDTO getMyInProgressMissions(Long memberId, int page) {

        // 1. 회원 존재 여부 검증 (MemberException 사용)
        //exists로 검증하면 boolean 값만넘어와서 효율적이다..
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MEMBER_NOT_FOUND));

        // (주의: findById 결과 자체는 사용하지 않으므로 변수에 저장할 필요는 없음)

        // 2. 미션 상태 및 Pageable 객체 생성
        MissionStatus status = MissionStatus.IN_PROGRESS;
        //프론트엔드에서 받은 page 번호(1부터 시작)를 DB의 0 기반 인덱스로 변환하는 핵심 과정입니다.
        // 한 페이지에 10개씩 조회, page는 0부터 시작하도록 -1 처리
        Pageable pageable = PageRequest.of(page - 1, 10);

        // 3. Repository 호출 (오류 없음)
        Page<UserMission> userMissions =
                userMissionRepository.findAllByMemberIdAndStatus(memberId, status, pageable);

        // 4. Converter를 이용해 DTO로 변환 (누락된 반환 로직 추가)
        return MissionConverter.toMyMissionListDTO(userMissions);
    }
}