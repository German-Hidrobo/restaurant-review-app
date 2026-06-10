package com.hidrobo.course.restaurant_review_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hidrobo.course.restaurant_review_app.domain.dtos.PhotoDto;
import com.hidrobo.course.restaurant_review_app.mappers.PhotoMapper;
import com.hidrobo.course.restaurant_review_app.services.PhotoService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class PhotoController {

        private final PhotoService photoService;
        private final PhotoMapper photoMapper;

        @PostMapping("/upload")
        public ResponseEntity<PhotoDto> uploadPhoto(
                        @RequestParam("file") MultipartFile file) {
                return new ResponseEntity<>(
                                photoMapper.toDto(photoService.uploadPhoto(file)),
                                HttpStatus.CREATED);

        }

        @SuppressWarnings("null")
        @GetMapping("/{filename:.+}")
        public ResponseEntity<Resource> getPhoto(@PathVariable String filename) {
                Optional<Resource> photoOptional = photoService.getPhotoAsResource(filename);
                return photoOptional.map(photo -> ResponseEntity.ok()
                                .contentType(MediaTypeFactory.getMediaType(photo)
                                                .orElse(MediaType.APPLICATION_OCTET_STREAM))
                                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                                .body(photo))
                                .orElse(ResponseEntity.notFound().build());
        }

}
