package com.example.foodmaster.domain.member.entity;

import com.example.foodmaster.domain.address.entity.DetailAddress;
import com.example.foodmaster.domain.member.entity.mapping.MemberFood;
import com.example.foodmaster.domain.member.entity.mapping.MemberMission;
import com.example.foodmaster.domain.member.entity.mapping.MemberTerm;
import com.example.foodmaster.domain.member.enums.Gender;
import com.example.foodmaster.domain.member.enums.LoginType;
import com.example.foodmaster.domain.review.entity.Review;
import com.example.foodmaster.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "nickname", length = 20, nullable = false)
    private String nickName;

    @Column(name = "login_type", nullable = false)
    private LoginType loginType;

    @Column(name = "point")
    private int point;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE)
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberTerm> memberTermList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_address_id")
    private DetailAddress detailAddress;

    @OneToMany(mappedBy = "member")
    private List<Review> reviewList = new ArrayList<>();
}
