package com.hidrobo.course.restaurant_review_app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.hidrobo.course.restaurant_review_app.domain.dtos.CreateUserRequest;
import com.hidrobo.course.restaurant_review_app.domain.models.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toEntity(CreateUserRequest userRequest);

}
