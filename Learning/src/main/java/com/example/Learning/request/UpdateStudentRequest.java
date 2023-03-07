package com.example.Learning.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateStudentRequest {

    @NotNull(message = "Student id are required")
    private Long id;

    private String firstName;
    private String lastName;
}
