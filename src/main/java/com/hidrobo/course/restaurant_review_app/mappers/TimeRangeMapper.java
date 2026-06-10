package com.hidrobo.course.restaurant_review_app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.hidrobo.course.restaurant_review_app.domain.dtos.CreateTimeRangeRequest;
import com.hidrobo.course.restaurant_review_app.domain.dtos.TimeRangeDto;
import com.hidrobo.course.restaurant_review_app.domain.models.TimeRange;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TimeRangeMapper {

    TimeRange toEntity(CreateTimeRangeRequest createTimeRangeRequest);
    
    TimeRange toEntity(TimeRangeDto timeRangeRequest);

    TimeRangeDto toDto(TimeRange timeRange);

    void updateEntityFromDto(TimeRangeDto dto, @MappingTarget TimeRange entity);

}
