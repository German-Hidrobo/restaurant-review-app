package com.hidrobo.course.restaurant_review_app.mappers;

import com.hidrobo.course.restaurant_review_app.domain.dtos.PhotoDto;
import com.hidrobo.course.restaurant_review_app.domain.models.Photo;

public interface PhotoMapper {

    PhotoDto toDto(Photo photo);

    Photo toEntity(PhotoDto photoDto);
}
