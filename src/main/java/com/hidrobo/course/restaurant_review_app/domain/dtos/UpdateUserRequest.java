package com.hidrobo.course.restaurant_review_app.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UpdateUserRequest(

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 100, message = "Username must be {min} to {max} characters")
    String username, 

    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 100, message = "First name must be {min} to {max} characters")
    String firstName,

    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 100, message = "Last name must be {min} to {max} characters")
    String lastName,

    @NotBlank(message = "Email is required")
    @Size(min = 5, max = 100, message = "Email must be {min} to {max} characters")
    @Email(message = "Please enter a valid email address.")
    String email,

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 150, message = "Password must be between {min} and {max} characters")
    String password
) {


}
