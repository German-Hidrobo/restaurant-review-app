package com.hidrobo.course.restaurant_review_app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.hidrobo.course.restaurant_review_app.domain.dtos.CreateReviewRequest;
import com.hidrobo.course.restaurant_review_app.domain.models.Review;

public interface ReviewService {

    List<Review> getReviewsByRestaurant(UUID restaurantId);

    Review getReviewById(UUID reviewId);

    void deleteReview(UUID reviewId, UUID userId);

    Review createReview(UUID userId, UUID restaurantId, CreateReviewRequest reviewRequest, List<MultipartFile> files);

    Review updateReview(UUID userId, UUID restaurantId, CreateReviewRequest reviewRequest);


}
