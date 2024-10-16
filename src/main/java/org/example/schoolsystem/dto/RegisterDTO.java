package org.example.schoolsystem.dto;

import lombok.Data;
import org.example.schoolsystem.model.user.UserRole;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private UserRole role;
}
