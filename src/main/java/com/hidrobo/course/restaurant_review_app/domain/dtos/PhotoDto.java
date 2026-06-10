package com.hidrobo.course.restaurant_review_app.domain.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;

@Builder
public record PhotoDto(
    UUID id,
    String filename,
    LocalDateTime uploadDate
) {

}
