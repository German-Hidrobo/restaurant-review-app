package com.hidrobo.course.restaurant_review_app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.hidrobo.course.restaurant_review_app.domain.dtos.CreateRestaurantRequest;
import com.hidrobo.course.restaurant_review_app.domain.dtos.UpdateRestaurantRequest;
import com.hidrobo.course.restaurant_review_app.domain.models.Restaurant;

public interface RestaurantService {

    Restaurant createRestaurant(CreateRestaurantRequest restaurantRequest, List<MultipartFile> files);

    void deleteRestaurantById(UUID id);

    Restaurant getRestaurantById(UUID id);

    List<Restaurant> getRestaurants();

    Restaurant updateRestaurant(UUID id, UpdateRestaurantRequest restaurantRequest);
}
