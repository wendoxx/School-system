package org.example.schoolsystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "teacher")
@Data
public class TeacherModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private Set<ClassModel> SchoolClass;

}
