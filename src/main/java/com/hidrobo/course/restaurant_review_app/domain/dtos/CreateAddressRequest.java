package com.hidrobo.course.restaurant_review_app.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreateAddressRequest(

        @NotBlank(message = "Street number is required") 
        @Size(max = 6, message = "Invalid street number format")
        String streetNumber,
        @NotBlank(message = "Street name is required") 
        String streetName,
        String unit,
        @NotBlank(message = "City is required") 
        String city,
        @NotBlank(message = "State is required") 
        String state,
        @NotBlank(message = "Postal code is required") 
        String postalCode,
        @NotBlank(message = "Country") 
        String country) {

}
