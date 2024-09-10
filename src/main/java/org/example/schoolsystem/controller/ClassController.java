package org.example.schoolsystem.controller;

import lombok.AllArgsConstructor;
import org.example.schoolsystem.dto.ClassDTO;
import org.example.schoolsystem.model.ClassModel;
import org.example.schoolsystem.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v2/class")
@AllArgsConstructor
public class ClassController {

    private final ClassService classService;

    @GetMapping("/{id}")
    public ResponseEntity<ClassModel> getClassById(@PathVariable UUID id){
        return ResponseEntity.ok(classService.findClassById(id));
    }

    @PostMapping
    public ResponseEntity<ClassModel> saveClass(@RequestBody ClassDTO classDTO){
        ClassModel savedClass = classService.saveClass(classDTO);
        return ResponseEntity.status(201).body(savedClass);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ClassModel> deleteClassById(@PathVariable UUID id) {
        classService.deleteClassById(id);
        return ResponseEntity.noContent().build();
    }

}
