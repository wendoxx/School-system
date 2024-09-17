package org.example.schoolsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.example.schoolsystem.dto.ClassDTO;
import org.example.schoolsystem.model.ClassModel;
import org.example.schoolsystem.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/class")
@AllArgsConstructor
@Tag(name = "Class controller", description = "This controller is responsible for handling class related operations")
public class ClassController {

    private final ClassService classService;

    @Operation(
            method = "GET",
            summary = "Get class by id",
            description = "This endpoint retrieves a class by its id")
    @ApiResponse(responseCode = "200", description = "Class retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Class not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @GetMapping("/{id}")
    public ResponseEntity<ClassModel> getClassById(@PathVariable UUID id){
        return ResponseEntity.ok(classService.findClassById(id));
    }

    @Operation(
            method = "GET",
            summary = "Get all classes",
            description = "This endpoint retrieves all classes")
    @ApiResponse(responseCode = "200", description = "Classes retrieved successfully")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @GetMapping("/getAll")
    public ResponseEntity<List<ClassModel>> getAllClasses(){
        return ResponseEntity.ok(classService.findAllClasses());
    }

    @Operation(
            method = "POST",
            summary = "Save class",
            description = "This endpoint saves a class")
    @ApiResponse(responseCode = "201", description = "Class saved successfully")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @PostMapping
    public ResponseEntity<ClassModel> saveClass(@RequestBody ClassDTO classDTO){
        ClassModel savedClass = classService.saveClass(classDTO);
        return ResponseEntity.status(201).body(savedClass);
    }

    @Operation(
            method = "PUT",
            summary = "Update class",
            description = "This endpoint updates a class")
    @ApiResponse(responseCode = "200", description = "Class updated successfully")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @PutMapping
    public ResponseEntity<ClassModel> updateClass(@RequestBody ClassDTO classDTO){
        ClassModel updatedClass = classService.saveClass(classDTO);
        return ResponseEntity.ok(updatedClass);
    }

    @Operation(
            method = "DELETE",
            summary = "Delete class",
            description = "This endpoint deletes a class")
    @ApiResponse(responseCode = "204", description = "Class deleted successfully")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Bad request")
   @DeleteMapping("/delete/{id}")
    public ResponseEntity<ClassModel> deleteClassById(@PathVariable UUID id) {
        classService.deleteClassById(id);
        return ResponseEntity.noContent().build();
    }

}
