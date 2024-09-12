package org.example.schoolsystem.controller;

import lombok.AllArgsConstructor;
import org.example.schoolsystem.model.TeacherModel;
import org.example.schoolsystem.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/teacher")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/{id}")
    public ResponseEntity<TeacherModel> getTeacherById(@PathVariable UUID id){
        return ResponseEntity.ok(teacherService.findTeacherById(id));
    }

    @GetMapping("/byName")
    public ResponseEntity<TeacherModel> getTeacherByName(@RequestParam String name){
        return ResponseEntity.ok(teacherService.findByName(name));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TeacherModel>> getAllTeachers(){
        return ResponseEntity.ok(teacherService.findAllTeachers());
    }

    @PostMapping
    public ResponseEntity<TeacherModel> saveTeacher(@RequestBody TeacherModel teacher){
        TeacherModel savedTeacher = teacherService.saveTeacher(teacher);
        return ResponseEntity.status(201).body(savedTeacher);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TeacherModel> deleteTeacherById(@PathVariable UUID id) {
        teacherService.deleteTeacherById(id);
        return ResponseEntity.noContent().build();
    }

}
