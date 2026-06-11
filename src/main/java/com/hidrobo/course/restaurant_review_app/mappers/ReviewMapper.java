package com.hidrobo.course.restaurant_review_app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.hidrobo.course.restaurant_review_app.domain.dtos.ReviewResponse;
import com.hidrobo.course.restaurant_review_app.domain.models.Review;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {

    ReviewResponse toDto(Review review);

}
