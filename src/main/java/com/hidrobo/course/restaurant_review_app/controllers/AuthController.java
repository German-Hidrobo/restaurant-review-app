package com.hidrobo.course.restaurant_review_app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hidrobo.course.restaurant_review_app.domain.dtos.AuthResponse;
import com.hidrobo.course.restaurant_review_app.domain.dtos.CreateUserRequest;
import com.hidrobo.course.restaurant_review_app.domain.dtos.LoginUserRequest;
import com.hidrobo.course.restaurant_review_app.domain.models.User;
import com.hidrobo.course.restaurant_review_app.services.AuthenticationService;
import com.hidrobo.course.restaurant_review_app.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

        private final AuthenticationService authenticationService;
        private final UserService userService;

        @PostMapping("/login")
        public ResponseEntity<AuthResponse> loadUser(@Valid @RequestBody LoginUserRequest userRequest) {
                return ResponseEntity.ok(authenticateUser(
                                userRequest.email(),
                                userRequest.password()));
        }

        @PostMapping("/register")
        public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody CreateUserRequest userRequest) {
                User user = userService.createUser(userRequest);
                String password = userRequest.password();

                return new ResponseEntity<>(
                                authenticateUser(user.getEmail(), password),
                                HttpStatus.CREATED);
        }



        private AuthResponse authenticateUser(String email, String password) {
                UserDetails userDetails = authenticationService.authenticate(
                                email, password);

                return AuthResponse.builder()
                                .token(authenticationService.generateToken(userDetails))
                                .expiresIn(86400l)
                                .build();
        }

}
