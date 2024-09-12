package org.example.schoolsystem.repository;

import org.example.schoolsystem.model.ClassModel;
import org.example.schoolsystem.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentModel, UUID> {
    // Find student by name
    StudentModel findByName(String name);
    // Find student by school class
    List<StudentModel> findBySchoolClass(ClassModel schoolClass);
}
