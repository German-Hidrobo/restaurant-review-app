package com.hidrobo.course.restaurant_review_app.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreateUserRequest(
        @Size(min = 3, max = 100, message = "Username must be {min} to {max} characters") 
        String username,
        
        @Size(min = 3, max = 100, message = "First name must be {min} to {max} characters") 
        String firstname,
        
        @Size(min = 3, max = 100, message = "Last name must be {min} to {max} characters") 
        String lastname,

        @Size(min = 5, max = 100, message = "Email must be {min} to {max} characteres") 
        @Email(message = "Please enter a valid email address.")
        String email,

        @Size(min = 6, max = 150, message = "Enter a combination of at least six numbers, letters and punctuation marks (such as ! and &).") 
        String password


) {

}
