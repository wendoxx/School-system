package org.example.schoolsystem.service;

import lombok.AllArgsConstructor;
import org.example.schoolsystem.model.ClassModel;
import org.example.schoolsystem.repository.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ClassService {
    private final ClassRepository classRepository;

    public ClassModel findClassById(UUID id){
        return classRepository.findById(id).get();
    }

    public ClassModel saveClass(ClassModel classModel) {
        return classRepository.save(classModel);
    }

    public ClassModel deleteClassById(UUID id) {
        ClassModel classModel = classRepository.findById(id).get();
        classRepository.deleteById(id);
        return classModel;
    }

}
