package com.example.Learning.controller;


import com.example.Learning.entity.StudentEntity;
import com.example.Learning.request.CreateStudentRequest;
import com.example.Learning.request.InQueryRequest;
import com.example.Learning.request.UpdateStudentRequest;
import com.example.Learning.response.StudentResponse;
import com.example.Learning.service.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.example.Learning.response.StudentResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/student/")
public class StudentController {

    // Error < Warn < Info < Debug < Trace

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @GetMapping("getAll")
    public List<StudentResponse> getStudent(){

        logger.error("Inside Error");
        logger.warn("Inside Warning");
        logger.info("Inside Info");
//        logger.debug("Inside Debug");
//        logger.trace("Inside Trace");

        List<StudentEntity> studentList = studentService.getAllStudent();

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;
    }

    @GetMapping("/getByCity/{city}")
    public List<StudentResponse> getByCity (@PathVariable String city){
        List<StudentEntity> studentList = studentService.getByCity(city);

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;
    }

    @PostMapping("create")
    public StudentResponse createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest){
        StudentEntity studentEntity = studentService.createStudent(createStudentRequest);
        return new StudentResponse(studentEntity);

    }
    @PutMapping("update")
    public StudentResponse updateStudent(@Valid @RequestBody UpdateStudentRequest updateStudentRequest){
        StudentEntity studentEntity=studentService.updateStudent(updateStudentRequest);

        return new StudentResponse(studentEntity);

    }
//
//    @DeleteMapping("delete")
//    public String deleteStudent(@RequestParam long id){
//
//        return studentService.deleteStudent(id);
//
//    }
    @DeleteMapping("delete/{id}")
    public String deleteStudent(@PathVariable long id){
        return studentService.deleteStudent(id);
    }

    @GetMapping("getByFirstName/{firstName}")
    public List<StudentResponse> getByFirstName(@PathVariable String firstName){
        List<StudentEntity> studentList = studentService.getByFirstName(firstName);

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;

    }

    @GetMapping("getByFirstNameAndLastName/{firstName}/{lastName}")
    public List<StudentResponse> getByFirstNameAndLastName (@PathVariable String firstName,@PathVariable String lastName){

        List<StudentEntity> studentList = studentService.getByFirstNameAndLastName(firstName,lastName);

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;

    }

    @GetMapping("getByFirstNameOrLastName/{firstName}/{lastName}")
    public List<StudentResponse> getByFirstNameOrLastName (@PathVariable String firstName,@PathVariable String lastName){

        List<StudentEntity> studentList = studentService.getByFirstNameOrLastName(firstName,lastName);

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;

    }

    @GetMapping("getByFirstNameIn")
    public List<StudentResponse> getByFirstNameIn(@RequestBody InQueryRequest inQueryRequest){

        logger.info("InQueryRequest = " + inQueryRequest);

        List<StudentEntity> studentList = studentService.getByFirstNameIn(inQueryRequest);

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student));
        });

        logger.info("studentResponseList = " + studentResponseList);

        return studentResponseList;
    }

    @GetMapping("getAllWithPagination")
    public List<StudentResponse> getAllWithPagination(@RequestParam int pageNo, @RequestParam int pageSize){
        List<StudentEntity> studentList = studentService.getAllStudentsWithPagination(pageNo,pageSize);

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;
    }

    @GetMapping("getAllWithSorting")
    public List<StudentResponse> getAllStudentsWithSorting(){
        List<StudentEntity> studentList = studentService.getAllStudentsWithSorting();

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;
    }

    @GetMapping("like/{firstName}")
    public List<StudentResponse> like(@PathVariable String firstName){
        List<StudentEntity> studentList = studentService.like(firstName);

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;
    }

    @GetMapping("startsWith/{firstName}")
    public List<StudentResponse> startsWith(@PathVariable String firstName){
        List<StudentEntity> studentList = studentService.startsWith(firstName);

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;
    }

    @GetMapping("EndsWith/{firstName}")
    public List<StudentResponse> EndsWith(@PathVariable String firstName){
        List<StudentEntity> studentList = studentService.EndsWith(firstName);

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;
    }

    @GetMapping("JPQLFirstNameAndLastName/{firstName}/{lastName}")
    public List<StudentResponse> JPQLFirstNameAndLastName (@PathVariable String firstName,@PathVariable String lastName){

        List<StudentEntity> studentList = studentService.JPQLFirstNameAndLastName(firstName,lastName);

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;

    }

    @PutMapping("updateFirstName/{id}/{firstName}")
    public String updateStudentWithJPQL (@PathVariable Long id, @PathVariable String firstName){
        return studentService.updateStudentWithJPQL(id,firstName)+ " Student(s) updated";
    }


    @DeleteMapping("deleteFirstName/{firstName}")
    public String deleteStudentWithJPQL (@PathVariable String firstName){
        return studentService.deleteStudentWithJPQL(firstName)+ " Student(s) deleted";
    }



}
