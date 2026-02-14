package com.ActiFitFlowApp.controller;

import com.ActiFitFlowApp.dto.LoginRequest;
import com.ActiFitFlowApp.dto.LoginResponse;
import com.ActiFitFlowApp.dto.RegisterRequest;
import com.ActiFitFlowApp.dto.UserResponse;
import com.ActiFitFlowApp.model.User;
import com.ActiFitFlowApp.security.JwtUtils;
import com.ActiFitFlowApp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

   @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
       return ResponseEntity.ok(userService.register(registerRequest));
   }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {

            User user = userService.authenticate(loginRequest);
            String token = jwtUtils.generateToken(user.getId() , user.getRole().name());

            return ResponseEntity.ok(new LoginResponse(token , userService.mapToResponse(user))
            );
        }
        catch (AuthenticationException e) {
            return ResponseEntity.status(401).build();
        }
    }
}
