package com.hidrobo.course.restaurant_review_app.services.implementations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hidrobo.course.restaurant_review_app.domain.dtos.CreateReviewRequest;
import com.hidrobo.course.restaurant_review_app.domain.models.Photo;
import com.hidrobo.course.restaurant_review_app.domain.models.Restaurant;
import com.hidrobo.course.restaurant_review_app.domain.models.Review;
import com.hidrobo.course.restaurant_review_app.domain.models.User;
import com.hidrobo.course.restaurant_review_app.repository.ReviewRepository;
import com.hidrobo.course.restaurant_review_app.services.PhotoService;
import com.hidrobo.course.restaurant_review_app.services.RestaurantService;
import com.hidrobo.course.restaurant_review_app.services.ReviewService;
import com.hidrobo.course.restaurant_review_app.services.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantService restaurantService;
    private final UserService userService;
    private final PhotoService photoService;

    @Transactional(readOnly = true)
    @Override
    public List<Review> getReviewsByRestaurant(UUID restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        return reviewRepository.findAllByRestaurant(restaurant);
    }

    
    @Transactional(readOnly = true)
    @SuppressWarnings("null")
    @Override
    public Review getReviewById(UUID reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review dies not found with the ID: " + reviewId));
    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public Review createReview(UUID userId, UUID restaurantId, CreateReviewRequest reviewRequest,
            List<MultipartFile> files) {

        User user = userService.getUserById(userId);
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        List<Photo> photos = files.stream().map(photoService::uploadPhoto).toList();

        Review review = Review.builder()
                .content(reviewRequest.content())
                .rating(reviewRequest.rating())
                .restaurant(restaurant)
                .photos(photos)
                .writtenBy(user)
                .build();

        return reviewRepository.save(review);
    }

    @Transactional
    @Override
    public void deleteReview(UUID reviewId, UUID userId) {
        Review review = getReviewById(reviewId);

        if (!review.getWrittenBy().getId().equals(userId)) {
            throw new AccessDeniedException("Unauthorized, only the review's writter can delete it");
        }

        reviewRepository.delete(review);
    }

    
    @Transactional
    @Override
    public Review updateReview(UUID userId, UUID reviewId, CreateReviewRequest reviewRequest) {

        Review review = getReviewById(reviewId);

        if (!review.getWrittenBy().getId().equals(userId)) {
            throw new AccessDeniedException("Unauthorized, only the review's writter can update it");
        }

        review.setContent(reviewRequest.content());
        review.setRating(reviewRequest.rating());
        review.setLastEdited(LocalDateTime.now());

        throw new UnsupportedOperationException("Unimplemented method 'updateReview'");
    }

}
