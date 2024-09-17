package org.example.schoolsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.example.schoolsystem.dto.TeacherDTO;
import org.example.schoolsystem.model.TeacherModel;
import org.example.schoolsystem.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/teacher")
@AllArgsConstructor
@Tag(name = "Teacher controller", description = "This controller is responsible for handling teacher related operations")

public class TeacherController {

    private final TeacherService teacherService;

    @Operation(
            method = "GET",
            summary = "Get teacher by id",
            description = "This endpoint retrieves a teacher by its id")
    @ApiResponse(responseCode = "200", description = "Teacher retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Teacher not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable UUID id){
        return ResponseEntity.ok(teacherService.findTeacherById(id));
    }

    @Operation(
            method = "GET",
            summary = "Get teacher by name",
            description = "This endpoint retrieves a teacher by its name")
    @ApiResponse(responseCode = "200", description = "Teacher retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Teacher not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @GetMapping("/byName")
    public ResponseEntity<TeacherDTO> getTeacherByName(@RequestParam String name){
        return ResponseEntity.ok(teacherService.findByName(name));
    }

    @Operation(
            method = "GET",
            summary = "Get all teachers",
            description = "This endpoint retrieves all teachers")
    @ApiResponse(responseCode = "200", description = "Teachers retrieved successfully")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @GetMapping("/getAll")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers(){
        return ResponseEntity.ok(teacherService.findAllTeachers());
    }

    @Operation(
            method = "POST",
            summary = "Save teacher",
            description = "This endpoint saves a teacher")
    @ApiResponse(responseCode = "201", description = "Teacher saved successfully")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @PostMapping
    public ResponseEntity<TeacherDTO> saveTeacher(@RequestBody TeacherDTO teacher){
        TeacherDTO savedTeacher = teacherService.saveTeacher(teacher);
        return ResponseEntity.status(201).body(savedTeacher);
    }

    @Operation(
            method = "PUT",
            summary = "Update teacher",
            description = "This endpoint updates a teacher")
    @ApiResponse(responseCode = "201", description = "Teacher updated successfully")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @PutMapping
    public ResponseEntity<TeacherDTO> updateTeacher(@RequestBody TeacherDTO teacher){
        TeacherDTO updatedTeacher = teacherService.updateTeacher(teacher);
        return ResponseEntity.status(201).body(updatedTeacher);
    }

    @Operation(
            method = "DELETE",
            summary = "Delete teacher",
            description = "This endpoint deletes a teacher")
    @ApiResponse(responseCode = "204", description = "Teacher deleted successfully")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TeacherDTO> deleteTeacherById(@PathVariable UUID id) {
        teacherService.deleteTeacherById(id);
        return ResponseEntity.noContent().build();
    }

}
