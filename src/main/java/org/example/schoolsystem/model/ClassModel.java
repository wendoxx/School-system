package org.example.schoolsystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "class")
@Data
public class ClassModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherModel teacherModel;

    @OneToMany(mappedBy = "classModel", fetch = FetchType.LAZY)
    private Set<StudentModel> studentModels;
}
