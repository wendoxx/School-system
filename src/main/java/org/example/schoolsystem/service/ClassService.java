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

    public ClassModel findClassById(UUID id){
        LOGGER.info("Finding class by id: " + id);
        return classRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Class not found");
            return new NotFoundException("Class not found");
        });
    }


    public List<ClassModel> findAllClasses(){
        LOGGER.info("Finding all classes");
        return classRepository.findAll();
    }

    @Transactional
    public ClassModel saveClass(ClassDTO classDTO) {
        ClassModel classModel = new ClassModel();
        TeacherModel teacher = teacherRepository.findById(classDTO.getTeacherId())
                .orElseThrow(() -> new NotFoundException("Teacher not found"));

        classModel.setTeacher(teacher);
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
        return classModel;
    }

    public ClassModel deleteClassById(UUID id) {
        ClassModel classModel = classRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Class not found");
            return new NotFoundException("Class not found for deletion");
        });
        classRepository.deleteById(id);
        LOGGER.info("Class deleted successfully");
        return classModel;
    }

}
