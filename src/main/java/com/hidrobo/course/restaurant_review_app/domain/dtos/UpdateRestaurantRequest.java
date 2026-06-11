package com.hidrobo.course.restaurant_review_app.domain.dtos;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UpdateRestaurantRequest(
        @NotNull(message = "Restaurant id is required")
        UUID id,

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
        AddressDto address,

        @NotEmpty(message = "Operating hours are required")
        @Valid
        List<TimeRangeDto> operatingHours,

        List<PhotoDto> photos,
        UserDto createdBy) {

}
