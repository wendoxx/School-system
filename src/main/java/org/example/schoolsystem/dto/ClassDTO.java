package org.example.schoolsystem.dto;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class ClassDTO {
    private UUID id;
    private String subject;
    private UUID teacherId;
    private Set<String> students;
}
