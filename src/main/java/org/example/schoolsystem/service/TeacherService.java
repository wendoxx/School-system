package org.example.schoolsystem.service;

import lombok.AllArgsConstructor;
import org.example.schoolsystem.model.TeacherModel;
import org.example.schoolsystem.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherModel findTeacherById(UUID id){
        return teacherRepository.findById(id).get();
    }

    public TeacherModel findByName(String name){
        return teacherRepository.findByName(name);
    }

    public List<TeacherModel> findAllTeachers(){
        return teacherRepository.findAll();
    }

    public TeacherModel saveTeacher(TeacherModel teacher) {
        return teacherRepository.save(teacher);
    }

    public TeacherModel deleteTeacherById(UUID id) {
        TeacherModel teacher = teacherRepository.findById(id).get();
        teacherRepository.deleteById(id);
        return teacher;
    }

}
