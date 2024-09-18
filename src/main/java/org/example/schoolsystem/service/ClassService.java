package org.example.schoolsystem.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.example.schoolsystem.dto.ClassDTO;
import org.example.schoolsystem.exception.NotFoundException;
import org.example.schoolsystem.model.ClassModel;
import org.example.schoolsystem.model.StudentModel;
import org.example.schoolsystem.model.TeacherModel;
import org.example.schoolsystem.repository.ClassRepository;
import org.example.schoolsystem.repository.StudentRepository;
import org.example.schoolsystem.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClassService {
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(ClassService.class);
    private final ModelMapper modelMapper;

    public ClassDTO findClassById(UUID id) {
        LOGGER.info("Finding class by id: " + id);
        ClassModel classModel = modelMapper.map(classRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Class not found");
            return new NotFoundException("Class not found");
        }), ClassModel.class);
        return modelMapper.map(classModel, ClassDTO.class);
    }

    public ClassDTO findClassBySubject(String subject){
        LOGGER.info("Finding class by subject: " + subject);
        ClassModel classModel = modelMapper.map(classRepository.findBySubject(subject), ClassModel.class);

        if (classModel == null) {
            LOGGER.error("Class not found");
            throw new NotFoundException("Class not found");
        }

        LOGGER.info("Class found successfully");
        return modelMapper.map(classModel, ClassDTO.class);
    }


    public List<ClassDTO> findAllClasses(){
        LOGGER.info("Finding all classes");
        ClassModel classModel = modelMapper.map(classRepository.findAll(), ClassModel.class);
        if (classModel == null) {
            LOGGER.error("Class not found");
            throw new NotFoundException("Class not found");
        }
        return modelMapper.map(classRepository.findAll(), List.class);
    }

    @Transactional
    public ClassDTO saveAndUpdateClass(ClassDTO classDTO) {
        ClassModel classModel;

        if (classDTO.getId() != null && classRepository.existsById(classDTO.getId())) {
            classModel = classRepository.findById(classDTO.getId())
                    .orElseThrow(() -> new NotFoundException("Class not found"));
        } else {
            classModel = new ClassModel();
        }

        // Set teacher
        TeacherModel teacher = teacherRepository.findById(classDTO.getTeacherId())
                .orElseThrow(() -> new NotFoundException("Teacher not found"));
        classModel.setTeacher(teacher);

        // Set subject
        classModel.setSubject(classDTO.getSubject());
        // Set students
        Set<StudentModel> students = classDTO.getStudents().stream()
                .map(UUID::fromString)
                .map(studentRepository::findById)
                .map(optionalStudent -> optionalStudent.orElseThrow( () -> new NotFoundException("Student not found")))
                .collect(Collectors.toSet());
        students.forEach(student -> student.setSchoolClass(classModel));
        classModel.setStudents(students);
        classRepository.save(classModel);
        LOGGER.info("Class saved successfully");
        return modelMapper.map(classModel, ClassDTO.class);
    }

    @Transactional
    public ClassDTO saveClass(ClassDTO classDTO){
        LOGGER.info("Saving class...");
        return saveAndUpdateClass(classDTO);
    }

    @Transactional
    public ClassDTO updateClass(ClassDTO classDTO){
        LOGGER.info("Updating class...");
        return saveAndUpdateClass(classDTO);
    }

    public void deleteClassById(UUID id) {
        LOGGER.info("Deleting class...");

        if (!classRepository.existsById(id)){
            LOGGER.error("Class not found");
            throw new NotFoundException("Class not found");
        }

        classRepository.deleteById(id);
        LOGGER.info("Class deleted successfully");
    }

}
