package com.hidrobo.course.restaurant_review_app.services.implementations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hidrobo.course.restaurant_review_app.domain.models.Photo;
import com.hidrobo.course.restaurant_review_app.exceptions.StorageException;
import com.hidrobo.course.restaurant_review_app.repository.PhotoRepository;
import com.hidrobo.course.restaurant_review_app.services.StorageService;

import com.hidrobo.course.restaurant_review_app.services.PhotoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final StorageService storageService;

    @SuppressWarnings("null")
    @Transactional
    @Override
    public Photo uploadPhoto(MultipartFile file) {

        UUID photoId = UUID.randomUUID();
        String filename = storageService.store(file, photoId);

        Photo photo = Photo.builder()
                .id(photoId)
                .filename(filename)
                .uploadDate(LocalDateTime.now())
                .build();

        return photoRepository.save(photo);

    }

    @Override
    public Optional<Resource> getPhotoAsResource(String filename) {
        return storageService.loadAsResource(filename);
    }

    @Override
    public Optional<Resource> getPhotoAsResourceById(UUID photoId) {

        return storageService.loadAsResource(getPhotoById(photoId));
    }

    @SuppressWarnings("null")
    public Photo getPhotoById(UUID photoId) {
        return photoRepository.findById(photoId)
                .orElseThrow(() -> new StorageException("Photo does not found with the ID: " + photoId));
    }

}
