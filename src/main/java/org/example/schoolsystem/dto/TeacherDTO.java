package org.example.schoolsystem.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TeacherDTO {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String address;
}
