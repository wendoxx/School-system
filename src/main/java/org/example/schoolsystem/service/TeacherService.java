package org.example.schoolsystem.service;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.example.schoolsystem.dto.TeacherDTO;
import org.example.schoolsystem.exception.NotFoundException;
import org.example.schoolsystem.model.TeacherModel;
import org.example.schoolsystem.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(TeacherService.class);
    private final ModelMapper modelMapper;

    public TeacherDTO findTeacherById(UUID id) {
        LOGGER.info("Finding teacher by id: {}", id);
        TeacherModel teacher = modelMapper.map(teacherRepository.findById(id).orElseThrow(() -> {

            LOGGER.error("Teacher not found");
            return new NotFoundException("Teacher not found");
        }), TeacherModel.class);

        return modelMapper.map(teacher, TeacherDTO.class);
    }

    public TeacherDTO findByName(String name){
        LOGGER.info("Finding teacher by name: {}", name);
        TeacherModel teacher = modelMapper.map(teacherRepository.findByName(name), TeacherModel.class);

        if (teacher == null) {
            LOGGER.error("Teacher not found");
            throw new NotFoundException("Teacher not found");
        }
        return modelMapper.map(teacher, TeacherDTO.class);
    }

    public List<TeacherDTO> findAllTeachers(){
        TeacherModel teacher = modelMapper.map(teacherRepository.findAll(), TeacherModel.class);
        LOGGER.info("Finding all teachers");

        if (teacher == null) {
            LOGGER.error("Teacher not found");
            throw new NotFoundException("Teacher not found");
        }

        return modelMapper.map(teacherRepository.findAll(), List.class);
    }

    public TeacherDTO saveAndUpdateTeacher(TeacherDTO teacherDTO){
        TeacherModel teacher;
        if (teacherDTO.getId() != null && teacherRepository.existsById(teacherDTO.getId())) {
            teacher = teacherRepository.findById(teacherDTO.getId()).orElseThrow(() -> {
                LOGGER.error("Teacher not found");
                return new NotFoundException("Teacher not found");
            });
        } else {
            teacher = new TeacherModel();
        }

        teacher.setName(teacherDTO.getName());
        teacher.setAddress(teacherDTO.getAddress());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setPhone(teacherDTO.getPhone());
        LOGGER.info("Teacher saved successfully");
        return modelMapper.map(teacherRepository.save(teacher), TeacherDTO.class);
    }


    public TeacherDTO saveTeacher(TeacherDTO teacherDTO){
        LOGGER.info("Saving teacher");
        return saveAndUpdateTeacher(teacherDTO);
    }

    public TeacherDTO updateTeacher(TeacherDTO teacherDTO){
        LOGGER.info("Updating teacher");
        if (!teacherRepository.existsById(teacherDTO.getId())) {
            LOGGER.error("Teacher not found");
            throw new NotFoundException("Teacher not found");
        }
        return saveAndUpdateTeacher(teacherDTO);
    }

    public void deleteTeacherById(UUID id) {
        LOGGER.info("Deleting teacher...");
        if (!teacherRepository.existsById(id)) {
            LOGGER.error("Teacher not found");
            throw new NotFoundException("Teacher not found");
        }
        LOGGER.info("Teacher deleted successfully");
        teacherRepository.deleteById(id);
    }
}
