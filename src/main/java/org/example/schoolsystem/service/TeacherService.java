package org.example.schoolsystem.service;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.example.schoolsystem.dto.TeacherDTO;
import org.example.schoolsystem.model.TeacherModel;
import org.example.schoolsystem.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(TeacherService.class);

    public TeacherModel findTeacherById(UUID id){
        LOGGER.info("Finding teacher by id: " + id);
        return teacherRepository.findById(id).get();
    }

    public TeacherModel findByName(String name){
        LOGGER.info("Finding teacher by name: " + name);
        return teacherRepository.findByName(name);
    }

    public List<TeacherModel> findAllTeachers(){
        LOGGER.info("Finding all teachers");
        return teacherRepository.findAll();
    }

    public TeacherModel saveTeacher(TeacherDTO teacher) {
        TeacherModel teacherModel = new TeacherModel();
        teacherModel.setName(teacher.getName());
        teacherModel.setAddress(teacher.getAddress());
        teacherModel.setEmail(teacher.getEmail());
        teacherModel.setPhone(teacher.getPhone());
        LOGGER.info("Teacher saved successfully");
        return teacherRepository.save(teacherModel);
    }

    public TeacherModel deleteTeacherById(UUID id) {
        TeacherModel teacher = teacherRepository.findById(id).get();
        teacherRepository.deleteById(id);
        LOGGER.info("Teacher deleted successfully");
        return teacher;
    }

}
