package com.hidrobo.course.restaurant_review_app.services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hidrobo.course.restaurant_review_app.domain.dtos.CreateUserRequest;
import com.hidrobo.course.restaurant_review_app.domain.dtos.UpdateUserRequest;
import com.hidrobo.course.restaurant_review_app.domain.enums.RoleEnum;
import com.hidrobo.course.restaurant_review_app.domain.models.User;
import com.hidrobo.course.restaurant_review_app.repository.UserRepository;
import com.hidrobo.course.restaurant_review_app.services.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @SuppressWarnings("null")
    @Override
    public User createUser(CreateUserRequest userRequest) {
        User user = User.builder()
                .username(userRequest.username())
                .firstname(userRequest.firstname())
                .lastname(userRequest.lastname())
                .email(userRequest.email())
                .role(RoleEnum.USER)
                .password(passwordEncoder.encode(userRequest.password()))
                .build();

        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User does not found with the ID: " + id));
    }

    @SuppressWarnings("null")
    @Override
    public void deleteUser(UUID id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    public User updateUser(UUID id, UpdateUserRequest userRequest) {

 
        
        User user = getUserById(id);
        user.setUsername(userRequest.username());
        user.setFirstname(userRequest.firstName());
        user.setLastname(userRequest.lastName());
        user.setEmail(userRequest.email());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        return userRepository.save(user);
    }

}
