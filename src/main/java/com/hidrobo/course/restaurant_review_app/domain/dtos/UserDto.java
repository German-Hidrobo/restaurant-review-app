package com.hidrobo.course.restaurant_review_app.domain.dtos;

import java.util.UUID;

import com.hidrobo.course.restaurant_review_app.domain.enums.RoleEnum;

import lombok.Builder;

@Builder
public record UserDto(

    UUID id,
    String username,
    String firstname,
    String lastname, 
    String email,
    RoleEnum role

) {

}
