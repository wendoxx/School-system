package org.example.schoolsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Student controller", description = "This controller is responsible for handling student related operations")
public class StudentController {

    private final StudentService studentService;

    @Operation(
            method = "GET",
            summary = "Get student by id",
            description = "This endpoint retrieves a student by its id")
    @ApiResponse(responseCode = "200", description = "Student retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Student not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @GetMapping("/{id}")
    public ResponseEntity<StudentModel> getStudentById(@PathVariable UUID id){
        return ResponseEntity.ok(studentService.findStudentById(id));
    }

    @Operation(
            method = "GET",
            summary = "Get student by name",
            description = "This endpoint retrieves a student by its name")
    @ApiResponse(responseCode = "200", description = "Student retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Student not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @GetMapping("/byName")
    public ResponseEntity<StudentModel> getStudentByName(String name){
        return ResponseEntity.ok(studentService.findByName(name));
    }

    @Operation(
            method = "GET",
            summary = "Get all students",
            description = "This endpoint retrieves all students")
    @ApiResponse(responseCode = "200", description = "Students retrieved successfully")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @GetMapping("/getAll")
    public ResponseEntity<List<StudentModel>> getAllStudents(){
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @Operation(
            method = "GET",
            summary = "Get students by class",
            description = "This endpoint retrieves all students in a class")
    @ApiResponse(responseCode = "200", description = "Students retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Class not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/byClass/{id}")
    public ResponseEntity<List<StudentModel>> getStudentByClass(@PathVariable UUID id){
        ClassDTO classDTO = new ClassDTO();
        classDTO.setId(id);
        return ResponseEntity.ok(studentService.findStudentsByClass(classDTO));
    }

    @Operation(
            method = "POST",
            summary = "Save student",
            description = "This endpoint saves a student")
    @ApiResponse(responseCode = "201", description = "Student saved successfully")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @PostMapping
    public ResponseEntity<StudentModel> saveStudent(@RequestBody StudentDTO student){
        StudentModel savedStudent = studentService.saveStudent(student);
        return ResponseEntity.status(201).body(savedStudent);
    }

    @Operation(
            method = "DELETE",
            summary = "Delete student",
            description = "This endpoint deletes a student")
    @ApiResponse(responseCode = "204", description = "Student deleted successfully")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StudentModel> deleteStudentById(@PathVariable UUID id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
}
