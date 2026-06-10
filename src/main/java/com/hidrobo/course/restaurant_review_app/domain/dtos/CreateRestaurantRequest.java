package com.hidrobo.course.restaurant_review_app.domain.dtos;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CreateRestaurantRequest(

    String name,
    String cuisineType,
    String contactInformation,
    @Valid
    CreateAddressRequest address,
    @NotEmpty
    @Valid
    List<CreateTimeRangeRequest> operatingHours
) {

}
