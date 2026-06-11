package com.hidrobo.course.restaurant_review_app.domain.dtos;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreateRestaurantRequest(

    @NotBlank(message = "Restaurant name is required")
    @Size(min = 2, max = 200, message = "Restaurant name must be {min} to {max} characters")
    String name,

    @NotBlank(message = "Cuisine type is required")
    @Size(min = 2, max = 100, message = "Cuisine type must be {min} to {max} characters")
    String cuisineType,

    @NotBlank(message = "Contact information is required")
    @Size(min = 5, max = 255, message = "Contact information must be {min} to {max} characters")
    String contactInformation,

    @NotNull(message = "Address is required")
    @Valid
    CreateAddressRequest address,

    @NotEmpty(message = "Operating hours are required")
    @Valid
    List<CreateTimeRangeRequest> operatingHours
) {

}
