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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor

public class StudentService {
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(StudentService.class);
    private final ModelMapper modelMapper;

    public StudentDTO findStudentById(UUID id) {
        LOGGER.info("Finding student by id: {}", id);

        StudentModel student = modelMapper.map(studentRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Student not found");
            return new NotFoundException("Student not found");
        }), StudentModel.class);

        return modelMapper.map(student, StudentDTO.class);
    }

    public StudentModel findByName(String name){
        LOGGER.info("Finding student by name: {}", name);
        StudentModel student = modelMapper.map(studentRepository.findByName(name), StudentModel.class);

        if (student == null) {
            LOGGER.error("Student not found");
            throw new NotFoundException("Student not found");
        }
        return student;
    }

    public List<StudentDTO> findStudentsByClass(ClassDTO classDTO){
        ClassModel schoolClass = classRepository.findById(classDTO.getId()).orElseThrow(() -> {
            LOGGER.error("Class not found");
            return new NotFoundException("Class not found");
        });
        LOGGER.info("Finding students by class.");
        return modelMapper.map(studentRepository.findBySchoolClass(schoolClass), List.class);

    }

    public List<StudentDTO> findAllStudents(){
        LOGGER.info("Finding all students");
        StudentModel student = modelMapper.map(studentRepository.findAll(), StudentModel.class);
        if(student == null){
            LOGGER.error("Student not found");
            throw new NotFoundException("Student not found");
        }
        return modelMapper.map(studentRepository.findAll(), List.class);
    }

    @Transactional
    public StudentDTO saveAndUpdateStudent(StudentDTO studentDTO){
        StudentModel student;
        if (studentDTO.getId() != null && studentRepository.existsById(studentDTO.getId())) {
            student = studentRepository.findById(studentDTO.getId()).orElseThrow(() -> new NotFoundException("Student not found"));
        } else {
            student = new StudentModel();
        }

        student.setName(studentDTO.getName());
        student.setAddress(studentDTO.getAddress());
        student.setEmail(studentDTO.getEmail());
        student.setPhone(studentDTO.getPhone());

        LOGGER.info("Student saved/updated successfully");
        return modelMapper.map(studentRepository.save(student), StudentDTO.class);
    }

    @Transactional
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        LOGGER.info("Saving student");
        return saveAndUpdateStudent(studentDTO);
    }

    @Transactional
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        LOGGER.info("Updating student");
        if (!studentRepository.existsById(studentDTO.getId())) {
            LOGGER.error("Student not found");
            throw new NotFoundException("Student not found");
        }
        return saveAndUpdateStudent(studentDTO);
    }

    public void deleteStudentById(UUID id) {
        LOGGER.info("deleting student...");
        if(!studentRepository.existsById(id)){
            LOGGER.error("Student not found");
            throw new NotFoundException("Student not found");
        }
        studentRepository.deleteById(id);
        LOGGER.info("Student deleted successfully");
    }

}
