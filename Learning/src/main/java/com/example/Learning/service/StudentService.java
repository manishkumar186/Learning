package com.example.Learning.service;


import com.example.Learning.entity.Address;
import com.example.Learning.entity.StudentEntity;
import com.example.Learning.entity.Subject;
import com.example.Learning.repository.AddressRepository;
import com.example.Learning.repository.CreateSubjectRequest;
import com.example.Learning.repository.StudentRepository;
import com.example.Learning.repository.SubjectRepository;
import com.example.Learning.request.CreateStudentRequest;
import com.example.Learning.request.InQueryRequest;
import com.example.Learning.request.UpdateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public List<StudentEntity> getAllStudent(){
        return studentRepository.findAll();
    }

    public StudentEntity createStudent(CreateStudentRequest createStudentRequest){
        StudentEntity studentEntity = new StudentEntity(createStudentRequest);

        Address address = new Address();
        address.setStreet(createStudentRequest.getStreet());
        address.setCity(createStudentRequest.getCity());

        address = addressRepository.save(address);

        studentEntity.setAddress(address);
        studentEntity = studentRepository.save(studentEntity);


        List<Subject> subjectList = new ArrayList<Subject>();

        if(createStudentRequest.getSubjectLearning() !=null){
            for(CreateSubjectRequest createSubjectRequest:createStudentRequest.getSubjectLearning()){
                Subject subject= new Subject();
                subject.setSubjectName(createSubjectRequest.getSubjectName());
                subject.setMarksObtained(createSubjectRequest.getMarksObtained());
                subject.setStudentEntity(studentEntity);

                subjectList.add(subject);

            }
            subjectRepository.saveAll(subjectList);

        }
        studentEntity.setSubjectLearning(subjectList);
        return  studentEntity;

    }
    public StudentEntity updateStudent(UpdateStudentRequest updateStudentRequest){
        StudentEntity studentEntity=studentRepository.findById(updateStudentRequest.getId()).get();

        if(updateStudentRequest.getFirstName() != null && !updateStudentRequest.getFirstName().isEmpty()){
            studentEntity.setFirstName(updateStudentRequest.getFirstName());
        }
        if(updateStudentRequest.getLastName() != null && !updateStudentRequest.getLastName().isEmpty()){
            studentEntity.setLastName(updateStudentRequest.getLastName());
        }
        studentRepository.save(studentEntity);

        return studentEntity;

    }
    public String deleteStudent(long id){
        studentRepository.deleteById(id);
        return "student id deleted successfully";
    }

    public List<StudentEntity>getByFirstName(String firstName){
        return studentRepository.findByFirstName(firstName);

    }

    public List<StudentEntity> getByFirstNameAndLastName(String firstName, String lastName){

        return studentRepository.findByFirstNameAndLastName(firstName,lastName);

    }

    public List<StudentEntity> getByFirstNameOrLastName(String firstName, String lastName){

        return studentRepository.findByFirstNameOrLastName(firstName,lastName);

    }

    public List<StudentEntity> getByFirstNameIn(InQueryRequest inQueryRequest) {

        return studentRepository.findByFirstNameIn(inQueryRequest.getFirstName());

    }

    public List<StudentEntity> getAllStudentsWithPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return studentRepository.findAll(pageable).getContent();
    }

    public List<StudentEntity> getAllStudentsWithSorting() {

        Sort sort = Sort.by(Sort.Direction.ASC,"firstName");
        return studentRepository.findAll(sort);
    }

    public List<StudentEntity> like(String firstName) {
        return studentRepository.findByFirstNameContains(firstName);
    }

    public List<StudentEntity> startsWith(String firstName) {

        return studentRepository.findByFirstNameStartsWith(firstName);
    }

    public List<StudentEntity> EndsWith(String firstName) {

        return studentRepository.findByFirstNameEndsWith(firstName);
    }


    public List<StudentEntity> JPQLFirstNameAndLastName(String firstName, String lastName) {
        return studentRepository.getByFirstNameAndLastName(firstName, lastName);
    }

    public Integer updateStudentWithJPQL(Long id, String firstName) {
        return studentRepository.UpdateFirstName(id,firstName);
    }


    public Integer deleteStudentWithJPQL(String firstName) {
        return studentRepository.DeleteFirstName(firstName);

    }

    public List<StudentEntity> getByCity(String city) {
        return studentRepository.getByAddressCity(city);
    }
}
