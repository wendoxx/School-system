package org.example.schoolsystem.controller;

import lombok.AllArgsConstructor;
import org.example.schoolsystem.dto.ClassDTO;
import org.example.schoolsystem.dto.StudentDTO;
import org.example.schoolsystem.model.StudentModel;
import org.example.schoolsystem.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentModel> getStudentById(@PathVariable UUID id){
        return ResponseEntity.ok(studentService.findStudentById(id));
    }

    @GetMapping("/byName")
    public ResponseEntity<StudentModel> getStudentByName(String name){
        return ResponseEntity.ok(studentService.findByName(name));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentModel>> getAllStudents(){
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @GetMapping("/byClass/{id}")
    public ResponseEntity<List<StudentModel>> getStudentByClass(@PathVariable UUID id){
        ClassDTO classDTO = new ClassDTO();
        classDTO.setId(id);
        return ResponseEntity.ok(studentService.findStudentsByClass(classDTO));
    }

    @PostMapping
    public ResponseEntity<StudentModel> saveStudent(@RequestBody StudentDTO student){
        StudentModel savedStudent = studentService.saveStudent(student);
        return ResponseEntity.status(201).body(savedStudent);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StudentModel> deleteStudentById(@PathVariable UUID id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
}
