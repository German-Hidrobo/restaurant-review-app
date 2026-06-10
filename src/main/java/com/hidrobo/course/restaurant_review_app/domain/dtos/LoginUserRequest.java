package com.hidrobo.course.restaurant_review_app.domain.dtos;

import lombok.Builder;

@Builder
public record LoginUserRequest(
        String email,
        String password) {

}
