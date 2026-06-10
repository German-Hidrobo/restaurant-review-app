package com.hidrobo.course.restaurant_review_app.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hidrobo.course.restaurant_review_app.domain.enums.RoleEnum;
import com.hidrobo.course.restaurant_review_app.domain.models.User;
import com.hidrobo.course.restaurant_review_app.repository.UserRepository;
import com.hidrobo.course.restaurant_review_app.security.JwtAuthenticationFilter;
import com.hidrobo.course.restaurant_review_app.services.AuthenticationService;
import com.hidrobo.course.restaurant_review_app.services.implementations.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/restaurants/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authenticationService) {
        return new JwtAuthenticationFilter(authenticationService);
    }

    @SuppressWarnings("null")
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        UserDetailsServiceImpl userDetailsServiceImpl = new UserDetailsServiceImpl(userRepository);
        List<User> users = userRepository.findAll();

        if (users.isEmpty() || users.size() == 0) {
            User user = User.builder()
                    .username("test")
                    .firstname("test")
                    .lastname("demo")
                    .email("test@demo.com")
                    .password(passwordEncoder().encode("test"))
                    .role(RoleEnum.USER)
                    .build();

            userRepository.save(user);
        }

        return userDetailsServiceImpl;

    }

}
