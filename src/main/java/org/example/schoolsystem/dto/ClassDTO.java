package org.example.schoolsystem.dto;

import lombok.Data;
import org.example.schoolsystem.model.TeacherModel;

import java.util.Set;
import java.util.UUID;

@Data
public class ClassDTO {
    private UUID id;
    private TeacherModel teacherModel;
    private Set<StudentDTO> studentDTOs;
}
