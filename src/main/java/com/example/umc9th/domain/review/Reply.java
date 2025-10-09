package com.example.umc9th.domain.review;

import com.example.umc9th.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "replies",
        indexes = @Index(name = "idx_replies_review", columnList = "review_id"))
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class Reply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "review_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_replies_review"))
    private Review review;

    /** 사장/운영자 유저: 삭제 시 NULL */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "fk_replies_user"))
    private User user;

    @Lob
    @Column(nullable = false)
    private String content;
}

