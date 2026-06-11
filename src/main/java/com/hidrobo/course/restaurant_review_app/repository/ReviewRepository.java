package com.hidrobo.course.restaurant_review_app.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hidrobo.course.restaurant_review_app.domain.models.Restaurant;
import com.hidrobo.course.restaurant_review_app.domain.models.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {

    @Query(value = "SELECT r FROM Review r INNER JOIN FETCH r.restaurant WHERE r.restaurant = :restaurant")
    List<Review> findAllByRestaurant(Restaurant restaurant);

}
