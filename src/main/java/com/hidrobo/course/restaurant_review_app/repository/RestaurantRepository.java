package com.hidrobo.course.restaurant_review_app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hidrobo.course.restaurant_review_app.domain.models.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID>{

    // @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.photos  LEFT JOIN FETCH r.address WHERE r.id = :id")
    // Optional<Restaurant> getRestaurantById(UUID id);
}
