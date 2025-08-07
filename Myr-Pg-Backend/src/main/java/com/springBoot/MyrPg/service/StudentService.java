package com.springBoot.MyrPg.service;

import com.springBoot.MyrPg.DTO.StudentRequestDTO;
import com.springBoot.MyrPg.DTO.StudentResponseDTO;
import com.springBoot.MyrPg.entity.Student;

import java.io.IOException;
import java.util.List;

public interface StudentService {

    Student registerStudent(StudentRequestDTO dto) throws IOException;//student come and register

    List<StudentResponseDTO> getAllStudents();//admin

    void deleteStudentById(String id); //admin


}
