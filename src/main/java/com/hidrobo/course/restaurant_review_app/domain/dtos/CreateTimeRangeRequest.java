package com.hidrobo.course.restaurant_review_app.domain.dtos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Builder
public record CreateTimeRangeRequest(
    @NotNull(message = "Day of week is required")
    DayOfWeek dayOfWeek,

    @NotNull(message = "Open time is required")
    LocalTime openTime,

    @NotNull(message = "Close time is required")
    LocalTime closeTime
) {

}
