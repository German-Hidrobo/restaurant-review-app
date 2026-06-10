package com.hidrobo.course.restaurant_review_app.domain.dtos;

import java.util.UUID;

import lombok.Builder;

@Builder
public record AddressDto(

        UUID id,
        String streetNumber,
        String streetName,
        String unit,
        String city,
        String state,
        String postalCode,
        String country

) {

}
