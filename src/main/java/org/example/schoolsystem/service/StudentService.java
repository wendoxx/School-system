package org.example.schoolsystem.service;

import lombok.AllArgsConstructor;
import org.example.schoolsystem.model.StudentModel;
import org.example.schoolsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentModel findStudentById(UUID id){
        return studentRepository.findById(id).get();
    }

    public StudentModel saveStudent(StudentModel student) {
        return studentRepository.save(student);
    }

    public StudentModel deleteStudentById(UUID id) {
        StudentModel student = studentRepository.findById(id).get();
        studentRepository.deleteById(id);
        return student;
    }

    public List<StudentModel> findAllStudents(){

        return studentRepository.findAll();
    }


    public StudentModel findByName(String name){

        return studentRepository.findByName(name);
    }
}
