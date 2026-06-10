package com.hidrobo.course.restaurant_review_app.services.implementations;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hidrobo.course.restaurant_review_app.domain.models.Photo;
import com.hidrobo.course.restaurant_review_app.exceptions.StorageException;
import com.hidrobo.course.restaurant_review_app.services.StorageService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileStorageServiceImpl implements StorageService {

    @Value("${app.storage.location:uploads}")
    private String storageLocation;

    private Path rootLocation;

    @Override
    public String store(MultipartFile file, UUID fileId) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store an empty file");
            }
            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String finalFilename = fileId + "." + extension;

            Path targetPath = this.rootLocation.resolve(Paths.get(finalFilename)).normalize().toAbsolutePath();

            if (!targetPath.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("Cannot store file outside current directory");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
            }

            return finalFilename;

        } catch (IOException e) {
            throw new StorageException("Failed to store file", e);
        }
    }

    @SuppressWarnings("null")
    @Override
    public Optional<Resource> loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return Optional.of(resource);
            }

            else {
                return Optional.empty();
            }

        } catch (MalformedURLException e) {
            log.debug("Could not read the file: " + filename);
            return Optional.empty();
        }
    }

    @PostConstruct
    public void init() {
        rootLocation = Paths.get(storageLocation);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }

    @SuppressWarnings("null")
    @Override
    public Optional<Resource> loadAsResource(Photo photo) {
        try {
            Path file = rootLocation.resolve(photo.getFilename());
            Resource resource;
            resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return Optional.of(resource);
            }

            else {
                return Optional.empty();
            }

        } catch (MalformedURLException e) {
            log.debug("Could not read this file.");
            return Optional.empty();
        }

    }
}
