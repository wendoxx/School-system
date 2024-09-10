package org.example.schoolsystem.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.schoolsystem.dto.ClassDTO;
import org.example.schoolsystem.model.ClassModel;
import org.example.schoolsystem.model.StudentModel;
import org.example.schoolsystem.model.TeacherModel;
import org.example.schoolsystem.repository.ClassRepository;
import org.example.schoolsystem.repository.StudentRepository;
import org.example.schoolsystem.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClassService {
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public ClassModel findClassById(UUID id){
        return classRepository.findById(id).get();
    }

    @Transactional
    public ClassModel saveClass(ClassDTO classDTO) {
        ClassModel classModel = new ClassModel();
        TeacherModel teacher = teacherRepository.findById(classDTO.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        classModel.setTeacher(teacher);

        //students is a set of student ids
        Set<StudentModel> students = classDTO.getStudents().stream()
                .map(UUID::fromString)
                .map(studentRepository::findById)
                .map(optionalStudent -> optionalStudent.orElseThrow( () -> new RuntimeException("Student not found")))
                .collect(Collectors.toSet());

        classModel.setStudents(students);
        return classRepository.save(classModel);

    }

    public ClassModel deleteClassById(UUID id) {
        ClassModel classModel = classRepository.findById(id).get();
        classRepository.deleteById(id);
        return classModel;
    }

}
