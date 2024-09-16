package org.example.schoolsystem.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.example.schoolsystem.dto.ClassDTO;
import org.example.schoolsystem.dto.StudentDTO;
import org.example.schoolsystem.exception.NotFoundException;
import org.example.schoolsystem.model.ClassModel;
import org.example.schoolsystem.model.StudentModel;
import org.example.schoolsystem.repository.ClassRepository;
import org.example.schoolsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor

public class StudentService {
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(StudentService.class);

    public StudentModel findStudentById(UUID id) {
        LOGGER.info("Finding student by id: {}", id);
        return studentRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Student not found");
            return new NotFoundException("Student not found");
        });
    }

    public StudentModel findByName(String name){
        LOGGER.info("Finding student by name: {}", name);
        StudentModel student = studentRepository.findByName(name);

        if (student == null) {
            LOGGER.error("Student not found");
            throw new NotFoundException("Student not found");
        }
        return student;
    }

    public List<StudentModel> findStudentsByClass(ClassDTO classDTO){
        ClassModel schoolClass = classRepository.findById(classDTO.getId()).orElseThrow(() -> {
            LOGGER.error("Class not found");
            return new NotFoundException("Class not found");
        });
        LOGGER.info("Finding students by class.");
        return studentRepository.findBySchoolClass(schoolClass);

    }

    @Transactional
    public StudentModel saveStudent(StudentDTO student) {

        StudentModel studentModel = new StudentModel();
        studentModel.setName(student.getName());
        studentModel.setAddress(student.getAddress());
        studentModel.setEmail(student.getEmail());
        studentModel.setPhone(student.getPhone());
        LOGGER.info("Student saved successfully");
        return studentRepository.save(studentModel);
    }

    public StudentModel deleteStudentById(UUID id) {
        StudentModel student = studentRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Student not found for deletion");
            return new NotFoundException("Student not found");
        });
        studentRepository.deleteById(id);
        LOGGER.info("Student deleted successfully");
        return student;
    }

    public List<StudentModel> findAllStudents(){
        LOGGER.info("Finding all students");
        return studentRepository.findAll();
    }

}
