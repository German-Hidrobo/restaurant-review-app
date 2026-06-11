package com.hidrobo.course.restaurant_review_app.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record CreateAddressRequest(

                @NotBlank(message = "Street number is required")
                @Pattern(regexp = "^[0-9]{1,5}[a-zA-Z]?$", message = "Invalid street number format")
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
                
                @NotBlank(message = "Country is required") 
                String country) {

}
