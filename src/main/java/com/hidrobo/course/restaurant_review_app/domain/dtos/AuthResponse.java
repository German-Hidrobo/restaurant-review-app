package com.hidrobo.course.restaurant_review_app.domain.dtos;

import lombok.Builder;

@Builder
public record AuthResponse(
    String token,
    Long expiresIn
) {

}
