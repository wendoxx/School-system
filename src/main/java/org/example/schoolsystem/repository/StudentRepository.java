package org.example.schoolsystem.repository;

import org.example.schoolsystem.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentModel, UUID> {
    StudentModel findByName(String name);
}
