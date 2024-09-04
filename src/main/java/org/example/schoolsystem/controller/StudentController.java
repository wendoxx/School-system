package org.example.schoolsystem.controller;

import lombok.AllArgsConstructor;
import org.example.schoolsystem.model.StudentModel;
import org.example.schoolsystem.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/getById")
    public ResponseEntity<StudentModel> getStudentById(UUID id){
        return ResponseEntity.ok(studentService.findStudentById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<StudentModel> saveStudent(StudentModel student){
        return ResponseEntity.ok(studentService.saveStudent(student));
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<StudentModel> deleteStudentById(UUID id) {
        return ResponseEntity.ok(studentService.deleteStudentById(id));
    }
}
