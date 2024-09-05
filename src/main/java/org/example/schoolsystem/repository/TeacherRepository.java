package org.example.schoolsystem.repository;

import org.example.schoolsystem.model.TeacherModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeacherRepository extends JpaRepository<TeacherModel, UUID> {
    TeacherModel findByName(String name);
}
