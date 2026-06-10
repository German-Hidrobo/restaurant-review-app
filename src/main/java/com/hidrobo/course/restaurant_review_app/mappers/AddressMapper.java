package com.hidrobo.course.restaurant_review_app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.hidrobo.course.restaurant_review_app.domain.dtos.AddressDto;
import com.hidrobo.course.restaurant_review_app.domain.dtos.CreateAddressRequest;
import com.hidrobo.course.restaurant_review_app.domain.models.Address;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {

    Address toEntity(CreateAddressRequest addressRequest);

    Address toEntity(AddressDto addressDto);

    void updateEntityFromDto(AddressDto dto, @MappingTarget Address entity);
}
