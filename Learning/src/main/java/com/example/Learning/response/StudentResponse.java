package com.example.Learning.response;

import com.example.Learning.entity.StudentEntity;
import com.example.Learning.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class StudentResponse {

    private long id;
    private String firstName;
    private String lastName;
    private String fullName;

    private String street;
    private String city;

    private List<SubjectResponse> learningSubject;


    public StudentResponse(StudentEntity studentEntity){
        this.id = studentEntity.getId();
        this.firstName = studentEntity.getFirstName();
        this.lastName = studentEntity.getLastName();
        this.fullName = studentEntity.getFirstName()+" "+studentEntity.getLastName();

        this.street = studentEntity.getAddress().getStreet();
        this.city = studentEntity.getAddress().getCity();

        if(studentEntity.getSubjectLearning()!= null){

            learningSubject= new ArrayList<SubjectResponse>();
            for(Subject subject:studentEntity.getSubjectLearning()){
                learningSubject.add(new SubjectResponse(subject));
            }
        }





    }


}
