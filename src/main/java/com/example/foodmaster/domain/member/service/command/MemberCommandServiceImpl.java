package com.example.foodmaster.domain.member.service.command;

import com.example.foodmaster.domain.food.entity.Food;
import com.example.foodmaster.domain.food.exception.FoodException;
import com.example.foodmaster.domain.food.exception.code.FoodErrorCode;
import com.example.foodmaster.domain.food.repository.FoodRepository;
import com.example.foodmaster.domain.member.converter.MemberConverter;
import com.example.foodmaster.domain.member.dto.join.MemberReqDTO;
import com.example.foodmaster.domain.member.dto.join.MemberResDTO;
import com.example.foodmaster.domain.member.entity.Member;
import com.example.foodmaster.domain.member.entity.mapping.MemberFood;
import com.example.foodmaster.domain.member.repository.MemberFoodRepository;
import com.example.foodmaster.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;

    @Override
    public MemberResDTO.JoinDTO signUp(
            MemberReqDTO.JoinDTO dto
    ) {
        // 사용자 생성
        Member member = MemberConverter.toMember(dto);
        // DB 적용
        memberRepository.save(member);

        if (dto.preferFoods().size() > 1) {
            List<MemberFood> memberFoodList = new ArrayList<>();

            // 선호 음식 ID별 조회
            for (MemberFood preferFood : dto.preferFoods()) {
                Food food = foodRepository.findById(preferFood.getId()).orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));

                // memberFood 엔티티 생성(컨버터 이용)
                MemberFood memberFood = MemberFood.builder()
                        .member(member)
                        .food(food)
                        .build();

                memberFoodList.add(memberFood);
            }

            // 모든 선호 음식 추가: DB 적용
            memberFoodRepository.saveAll(memberFoodList);
        }

        return MemberConverter.toJoinDTO(member);
    }
}
