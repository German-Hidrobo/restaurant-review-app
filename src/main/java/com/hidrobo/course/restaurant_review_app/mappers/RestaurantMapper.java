package com.hidrobo.course.restaurant_review_app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.hidrobo.course.restaurant_review_app.domain.dtos.RestaurantResponse;
import com.hidrobo.course.restaurant_review_app.domain.models.Restaurant;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {
    
    RestaurantResponse toDto(Restaurant restaurant);

}
