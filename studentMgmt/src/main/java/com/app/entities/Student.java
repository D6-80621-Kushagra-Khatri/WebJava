package com.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Student extends BaseEntity{
    @Column(length = 25, nullable = false)
    private String sName;
    @Column(length = 30, nullable = false)
    private String email;
    @Column(length = 25, nullable = false)
    private String password;
    @ManyToOne
    private Course course;
}
