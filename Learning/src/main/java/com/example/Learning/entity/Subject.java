package com.example.Learning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="subject_name")
    private String subjectName;

    @Column(name="marks_obtained")
    private double marksObtained;

    @ManyToOne
    @JoinColumn(name="student_id")
    private StudentEntity studentEntity;
}
