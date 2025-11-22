package com.example.foodmaster.domain.mission.service.query;

import com.example.foodmaster.domain.mission.converter.MissionConverter;
import com.example.foodmaster.domain.mission.dto.MissionResDTO;
import com.example.foodmaster.domain.mission.entity.Mission;
import com.example.foodmaster.domain.mission.repository.MissionRepository;
import com.example.foodmaster.domain.store.entity.Store;
import com.example.foodmaster.domain.store.exception.StoreException;
import com.example.foodmaster.domain.store.exception.code.StoreErrorCode;
import com.example.foodmaster.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    public MissionResDTO.MissionPreviewListDTO findMission(
            String storeName,
            Integer page
    ) {
        // 가게를 갖고 온다(가게 존재 여부도 검증)
        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Mission> result = missionRepository.findAllByStore(store, pageRequest);

        return MissionConverter.toMissionPreviewListDTO(result);
    }
}
