package com.example.Learning.entity;

import com.example.Learning.request.CreateStudentRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter

@NoArgsConstructor
@Entity
@Table(name="student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Transient
    private String fullName;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "studentEntity")
    private List<Subject> subjectLearning;

    public StudentEntity(CreateStudentRequest createStudentRequest){
        this.firstName = createStudentRequest.getFirstName();
        this.lastName = createStudentRequest.getLastName();
        this.fullName = createStudentRequest.getFirstName() +" "+ createStudentRequest.getLastName();
    }




}
