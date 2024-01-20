package com.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Course extends BaseEntity{
    @Column(length = 30, nullable = false)
    private String name;
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;
    @Column(nullable = false)
    private Double fees;
    @Column(nullable = false)
    private char gradeToPass;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Student> studentsEnrolled = new ArrayList<>();

    public void addStudent(Student stud)
    {
        studentsEnrolled.add(stud);
        stud.setCourse(this);
    }

}
