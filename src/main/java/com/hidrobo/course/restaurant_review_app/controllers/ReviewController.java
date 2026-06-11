package com.hidrobo.course.restaurant_review_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hidrobo.course.restaurant_review_app.domain.dtos.CreateReviewRequest;
import com.hidrobo.course.restaurant_review_app.domain.dtos.ReviewResponse;
import com.hidrobo.course.restaurant_review_app.mappers.ReviewMapper;
import com.hidrobo.course.restaurant_review_app.services.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/{restaurantId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getReviews(
            @PathVariable(name = "restaurantId") UUID restaurantId) {
        return ResponseEntity.ok(reviewService.getReviewsByRestaurant(restaurantId).stream()
                .map(reviewMapper::toDto)
                .toList());
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> getReviews(
            @PathVariable UUID restaurantId,
            @PathVariable UUID reviewId) {

        return ResponseEntity.ok(reviewMapper.toDto(reviewService.getReviewById(reviewId)));
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(
            @Valid @PathVariable(name = "restaurantId") UUID restaurantId,
            @Valid @RequestPart("restaurant") CreateReviewRequest reviewRequest,
            @RequestPart("files") List<MultipartFile> files,
            @RequestAttribute UUID userId) {

        return new ResponseEntity<ReviewResponse>(
                reviewMapper.toDto(reviewService.createReview(userId, restaurantId, reviewRequest, files)),
                HttpStatus.CREATED);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(
            @RequestAttribute UUID userId,
            @PathVariable UUID reviewId,
            @Valid @RequestBody CreateReviewRequest reviewRequest

    ) {
        return ResponseEntity.ok(reviewMapper.toDto(reviewService.updateReview(userId, reviewId, reviewRequest)));
    }


    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID reviewId, @RequestAttribute UUID userId){
        reviewService.deleteReview(reviewId, userId);

        return ResponseEntity.noContent().build();
    }
}
