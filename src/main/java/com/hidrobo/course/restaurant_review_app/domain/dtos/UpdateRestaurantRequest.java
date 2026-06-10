package com.hidrobo.course.restaurant_review_app.domain.dtos;

import java.util.List;
import java.util.UUID;

import lombok.Builder;

@Builder
public record UpdateRestaurantRequest(
        UUID id,
        String name,
        String cuisineType,
        String contactInformation,
        AddressDto address,
        List<TimeRangeDto> operatingHours,
        List<PhotoDto> photos,
        UserDto createdBy) {

}
