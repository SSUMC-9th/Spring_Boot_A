package com.example.umc9th.domain.food;

import com.example.umc9th.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_foods",
        uniqueConstraints = @UniqueConstraint(name = "uq_user_food", columnNames = {"user_id","food_id"}),
        indexes = @Index(name = "idx_user_food_rank", columnList = "user_id,rank_order"))
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class UserFood {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_food_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_foods_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "food_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_foods_food"))
    private Food food;

    @Column(name = "rank_order")
    private Integer rankOrder;

    private Integer score;
}

