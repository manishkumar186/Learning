package com.example.Learning.request;

import com.example.Learning.repository.CreateSubjectRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class CreateStudentRequest {

    @NotBlank(message = "first name is required")
    private String firstName;

    private String lastName;

    private String street;

    private String city;

    private List<CreateSubjectRequest> subjectLearning;
}
