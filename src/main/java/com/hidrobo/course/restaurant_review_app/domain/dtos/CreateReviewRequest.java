package com.hidrobo.course.restaurant_review_app.domain.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreateReviewRequest(
        @NotBlank(message = "Review content is required")
        @Size(min = 5, max = 2000, message = "Review content must be {min} to {max} characters")
        String content,

        @NotNull(message = "Rating is required")
        @Min(value = 1, message = "Rating must be at least {value}")
        @Max(value = 5, message = "Rating must be at most {value}")
        Integer rating
) {

}
