package com.hidrobo.course.restaurant_review_app.domain.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record ReviewResponse(

    UUID id,
    String content,
    Integer rating,
    LocalDateTime lastEdited,
    List<PhotoDto> photos,
    UserDto writtenBy
 
) {

}
