package com.hidrobo.course.restaurant_review_app.services.implementations;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hidrobo.course.restaurant_review_app.domain.dtos.CreateRestaurantRequest;
import com.hidrobo.course.restaurant_review_app.domain.dtos.TimeRangeDto;
import com.hidrobo.course.restaurant_review_app.domain.dtos.UpdateRestaurantRequest;
import com.hidrobo.course.restaurant_review_app.domain.models.Address;
import com.hidrobo.course.restaurant_review_app.domain.models.Photo;
import com.hidrobo.course.restaurant_review_app.domain.models.Restaurant;
import com.hidrobo.course.restaurant_review_app.domain.models.TimeRange;
import com.hidrobo.course.restaurant_review_app.mappers.AddressMapper;
import com.hidrobo.course.restaurant_review_app.mappers.TimeRangeMapper;
import com.hidrobo.course.restaurant_review_app.repository.RestaurantRepository;
import com.hidrobo.course.restaurant_review_app.services.PhotoService;
import com.hidrobo.course.restaurant_review_app.services.RestaurantService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final AddressMapper addressMapper;
    private final TimeRangeMapper timeRangeMapper;
    private final PhotoService photoService;

    @Transactional
    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest restaurantRequest, List<MultipartFile> files) {

        List<Photo> photos = files.stream().map(photoService::uploadPhoto).toList();

        List<TimeRange> operatingHours = restaurantRequest.operatingHours().stream()
                .map(timeRangeMapper::toEntity)
                .toList();

        Address address = addressMapper.toEntity(restaurantRequest.address());

        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.name());
        restaurant.setCuisineType(restaurantRequest.cuisineType());
        restaurant.setContactInformation(restaurantRequest.contactInformation());
        restaurant.setAddress(address);
        restaurant.setPhotos(photos);
        restaurant.addOperatingHours(operatingHours);
        
        return restaurantRepository.save(restaurant);
    }

    @SuppressWarnings("null")
    @Transactional
    @Override
    public void deleteRestaurantById(UUID id) {
        restaurantRepository.deleteById(id);
    }

    @SuppressWarnings("null")
    @Transactional(readOnly = true)
    @Override
    public Restaurant getRestaurantById(UUID id) {
        return restaurantRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("The restaurant does not found with the ID: " + id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @Transactional
    @Override
    public Restaurant updateRestaurant(UUID id, UpdateRestaurantRequest restaurantRequest) {
        Restaurant restaurantDb = getRestaurantById(id);

        restaurantDb.setName(restaurantRequest.name());
        restaurantDb.setCuisineType(restaurantRequest.cuisineType());
        restaurantDb.setContactInformation(restaurantRequest.contactInformation());

        if (restaurantRequest.address() != null && restaurantDb.getAddress() != null) {
            addressMapper.updateEntityFromDto(restaurantRequest.address(), restaurantDb.getAddress());
        } else {
            restaurantDb.setAddress(addressMapper.toEntity(restaurantRequest.address()));
        }

        updateOperatingHours(restaurantDb.getOperatingHours(), restaurantRequest.operatingHours());

        return restaurantDb;
    }

    private void updateOperatingHours(List<TimeRange> currentHours, List<TimeRangeDto> incomingHoursDtos) {
        if (incomingHoursDtos == null) {
            currentHours.clear();
            return;
        }
        List<UUID> incomingIds = incomingHoursDtos.stream()
                .map(TimeRangeDto::id)
                .filter(Objects::nonNull)
                .toList();
        currentHours.removeIf(existing -> !incomingIds.contains(existing.getId()));

        for (TimeRangeDto dto : incomingHoursDtos) {
            if (dto.id() != null) {
                currentHours.stream()
                        .filter(existing -> existing.getId().equals(dto.id()))
                        .findFirst()
                        .ifPresent(existing -> timeRangeMapper.updateEntityFromDto(dto, existing));
            } else {
                currentHours.add(timeRangeMapper.toEntity(dto));
            }
        }
    }
}
