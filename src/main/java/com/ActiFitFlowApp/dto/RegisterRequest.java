package com.ActiFitFlowApp.dto;

import com.ActiFitFlowApp.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email please try again !")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    private String firstName;
    private String lastName;
    private UserRole role;
}
