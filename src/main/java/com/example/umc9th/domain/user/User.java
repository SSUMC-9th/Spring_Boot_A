package com.example.umc9th.domain.user;

import com.example.umc9th.domain.common.BaseTimeEntity;
import com.example.umc9th.domain.enums.AuthProvider;
import com.example.umc9th.domain.enums.Gender;
import com.example.umc9th.domain.mission.UserMission;
import com.example.umc9th.domain.review.Reply;
import com.example.umc9th.domain.review.Review;
import com.example.umc9th.domain.term.UserTerm;
import com.example.umc9th.domain.food.UserFood;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_users_email", columnNames = "email"),
                @UniqueConstraint(name = "uq_users_provider", columnNames = {"provider", "provider_user_id"})
        })
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long id;

    @Email
    @Size(max = 255)
    @Column(length = 255, unique = true)
    private String email; // SNS면 NULL 허용

    @Size(max = 255)
    @Column(name = "password_hash", length = 255)
    private String passwordHash; // SNS면 NULL 허용

    @Column(length = 40, nullable = false)
    private String nickname;

    @Size(max = 30)
    @Column(length = 30)
    private String phone;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('M','F','OTHER') default 'OTHER'", nullable = false)
    private Gender gender = Gender.OTHER;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('EMAIL','KAKAO','NAVER','APPLE','GOOGLE','NONE') default 'NONE'")
    private AuthProvider provider = AuthProvider.NONE;

    @Size(max = 255)
    @Column(name = "provider_user_id", length = 255)
    private String providerUserId;

    /* Relations */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserMission> userMissions = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Reply> replies = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTerm> userTerms = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserFood> userFoods = new ArrayList<>();
}

