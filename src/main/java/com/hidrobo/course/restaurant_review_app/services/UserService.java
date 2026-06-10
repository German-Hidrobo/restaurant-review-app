package com.hidrobo.course.restaurant_review_app.services;

import java.util.List;
import java.util.UUID;

import com.hidrobo.course.restaurant_review_app.domain.dtos.CreateUserRequest;
import com.hidrobo.course.restaurant_review_app.domain.dtos.UpdateUserRequest;
import com.hidrobo.course.restaurant_review_app.domain.models.User;

public interface UserService {


    List<User> getUsers();

    User getUserById(UUID id);

    User createUser(CreateUserRequest userRequest);

    void deleteUser(UUID id);

    User updateUser(UUID id, UpdateUserRequest userRequest);
    

}
