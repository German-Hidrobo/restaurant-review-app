package com.hidrobo.course.restaurant_review_app.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import com.hidrobo.course.restaurant_review_app.domain.models.Photo;

public interface PhotoService {

    Photo uploadPhoto(MultipartFile file);
    Optional<Resource> getPhotoAsResource(String filename);
    Optional<Resource> getPhotoAsResourceById(UUID photoId);

}
