package com.hidrobo.course.restaurant_review_app.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.hidrobo.course.restaurant_review_app.domain.models.Photo;

public interface StorageService {

    String store(MultipartFile file, UUID fileId);

    Optional<Resource> loadAsResource(String filename);

    Optional<Resource> loadAsResource(Photo photo);
}
