package org.example.schoolsystem.repository;

import org.example.schoolsystem.model.ClassModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClassRepository extends JpaRepository<ClassModel, UUID> {
    ClassModel findBySubject (String subject);
}
