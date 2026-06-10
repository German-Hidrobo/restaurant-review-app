package com.hidrobo.course.restaurant_review_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hidrobo.course.restaurant_review_app.domain.dtos.CreateRestaurantRequest;
import com.hidrobo.course.restaurant_review_app.domain.dtos.RestaurantResponse;
import com.hidrobo.course.restaurant_review_app.domain.dtos.UpdateRestaurantRequest;
import com.hidrobo.course.restaurant_review_app.mappers.RestaurantMapper;
import com.hidrobo.course.restaurant_review_app.services.RestaurantService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> getRestaurantById(@PathVariable UUID id) {
        return ResponseEntity.ok(restaurantMapper.toDto(restaurantService.getRestaurantById(id)));
    }

    @GetMapping()
    public ResponseEntity<List<RestaurantResponse>> getRestaurnats() {
        return ResponseEntity.ok(restaurantService.getRestaurants().stream()
                .map(restaurantMapper::toDto).toList());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestaurantResponse> createRestaurant(
            @RequestPart("restaurant") CreateRestaurantRequest restaurantRequest,
            @RequestPart("files") List<MultipartFile> files) {

        return new ResponseEntity<>(
                restaurantMapper.toDto(restaurantService.createRestaurant(restaurantRequest, files)),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable UUID id) {
        restaurantService.deleteRestaurantById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponse> updateRestaurant(
        @PathVariable UUID id, 
        @RequestBody UpdateRestaurantRequest restaurantRequest) {
        return ResponseEntity.ok(restaurantMapper.toDto(
            restaurantService.updateRestaurant(id, restaurantRequest)));
    }
}
