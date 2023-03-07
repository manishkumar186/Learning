package com.example.Learning.repository;

import com.example.Learning.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {

    List<StudentEntity> findByFirstName (String firstName);
    List<StudentEntity> findByFirstNameAndLastName (String firstName, String lastName);
    List<StudentEntity> findByFirstNameOrLastName (String firstName, String lastName);

    List<StudentEntity> findByFirstNameIn (List<String> firstName);
    List<StudentEntity> findByFirstNameContains (String firstName);

    List<StudentEntity> findByFirstNameStartsWith(String firstName);

    List<StudentEntity> findByFirstNameEndsWith(String firstName);

    @Query("From StudentEntity where firstName = :firstName and lastName = :lastName")
    List<StudentEntity> getByFirstNameAndLastName(String firstName, String lastName);

    @Modifying
    @Transactional
    @Query("Update StudentEntity set firstName = :firstName where id = :id")
    Integer UpdateFirstName (Long id, String firstName);


    @Modifying
    @Transactional
    @Query("Delete From StudentEntity where firstName = :firstName")
    Integer DeleteFirstName (String firstName);

//    List<StudentEntity> findByAddressCity(String city);

    @Query("From StudentEntity where address.city = :city")
    List<StudentEntity> getByAddressCity(String city);
}
