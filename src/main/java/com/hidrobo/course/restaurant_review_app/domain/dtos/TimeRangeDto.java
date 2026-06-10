package com.hidrobo.course.restaurant_review_app.domain.dtos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;


import lombok.Builder;

@Builder
public record TimeRangeDto(
    UUID id,
    DayOfWeek dayOfWeek,
    LocalTime openTime,
    LocalTime closeTime
) {

}
