package com.example.umc9th.application.user.dto;
public record UserSummaryDto(
        Long userId, String nickname, String email, String phone,
        boolean phoneVerified, long myReviewCount
) {}

