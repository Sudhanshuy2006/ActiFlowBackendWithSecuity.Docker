package com.ActiFitFlowApp.service;

import com.ActiFitFlowApp.dto.LoginRequest;
import com.ActiFitFlowApp.dto.RegisterRequest;
import com.ActiFitFlowApp.dto.UserResponse;
import com.ActiFitFlowApp.model.User;
import com.ActiFitFlowApp.model.UserRole;
import com.ActiFitFlowApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserResponse register(RegisterRequest request) {
        UserRole role = request.getRole() != null ? request.getRole()
                : UserRole.USER;
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(role)
                .build();
//        User user =  new User(
//                null,
//                request.getEmail(),
//                request.getPassword(),
//                request.getFirstName(),
//                request.getLastName(),
//                Instant.parse("2025-12-08T14:49:41.208Z")
//                        .atZone(ZoneOffset.UTC)
//                        .toLocalDateTime(),
//                Instant.parse("2025-12-08T14:49:41.208Z")
//                        .atZone(ZoneOffset.UTC)
//                        .toLocalDateTime(),
//                List.of(),
//                List.of()
//        );
        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    public UserResponse mapToResponse(User savedUser) {
        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setPassword(savedUser.getPassword());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());
        return response;
    };

    public User authenticate(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null)
            throw new RuntimeException("Invalid User Please try again!");
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid User Please try again!");
        }
        return user;
    }
}
