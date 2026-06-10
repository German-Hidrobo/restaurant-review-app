package com.hidrobo.course.restaurant_review_app.domain.dtos;

import lombok.Builder;

@Builder
public record UpdateUserRequest(

    String username, 
    String firstName,
    String lastName,
    String email,
    String password
) {


}
