package com.hidrobo.course.restaurant_review_app.mappers.implementations;

import org.springframework.stereotype.Component;

import com.hidrobo.course.restaurant_review_app.domain.dtos.PhotoDto;
import com.hidrobo.course.restaurant_review_app.domain.models.Photo;
import com.hidrobo.course.restaurant_review_app.mappers.PhotoMapper;

@Component
public class PhotoMapperImpl implements PhotoMapper{

    @Override
    public PhotoDto toDto(Photo photo) {
       if ( photo == null ) {
            return null;
        }

        PhotoDto.PhotoDtoBuilder photoDto = PhotoDto.builder();

        photoDto.id( photo.getId() );
        photoDto.filename( photo.getFilename() );
        photoDto.uploadDate( photo.getUploadDate() );

        return photoDto.build();
    }

    @Override
    public Photo toEntity(PhotoDto photoDto) {
          if ( photoDto == null ) {
            return null;
        }

        Photo.PhotoBuilder photo = Photo.builder();

        photo.id( photoDto.id() );
        photo.filename( photoDto.filename() );
        photo.uploadDate( photoDto.uploadDate() );

        return photo.build();
    }

}
