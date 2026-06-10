package com.hidrobo.course.restaurant_review_app.domain.dtos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import lombok.Builder;


@Builder
public record CreateTimeRangeRequest(
    DayOfWeek dayOfWeek,
    LocalTime openTime,
    LocalTime closeTime
) {

}
