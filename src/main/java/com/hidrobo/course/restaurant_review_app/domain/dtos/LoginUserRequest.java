package com.hidrobo.course.restaurant_review_app.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record LoginUserRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Please enter a valid email address.")
        @Size(max = 100, message = "Email must be at most {max} characters")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 150, message = "Password must be between {min} and {max} characters")
        String password) {

}
