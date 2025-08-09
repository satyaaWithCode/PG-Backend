package com.springBoot.MyrPg.controller;

import com.springBoot.MyrPg.DTO.ApiResponseDTO;
import com.springBoot.MyrPg.DTO.StudentRequestDTO;
import com.springBoot.MyrPg.DTO.StudentResponseDTO;
import com.springBoot.MyrPg.entity.Student;
import com.springBoot.MyrPg.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/pg")
@CrossOrigin(origins = {"https://satyaa-pg.netlify.app", "http://localhost:5173"})
//@CrossOrigin(origins = "https://satyaa-pg.netlify.app" )
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;


    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponseDTO<Student>> register(
            @RequestPart("studentName") String studentName,
            @RequestPart("permanentAddress") String permanentAddress,
            @RequestPart("aadharCardNumber") String aadharCardNumber,
            @RequestPart("phoneNumber") String phoneNumber,
            @RequestPart("gmail") String gmail,
            @RequestPart("currentAddress") String currentAddress,
            @RequestPart("aadharFile") MultipartFile aadharFile
    ) throws Exception {

        StudentRequestDTO dto = new StudentRequestDTO();
        dto.setStudentName(studentName);
        dto.setPermanentAddress(permanentAddress);
        dto.setAadharCardNumber(aadharCardNumber);
        dto.setPhoneNumber(phoneNumber);
        dto.setGmail(gmail);
        dto.setCurrentAddress(currentAddress);
        dto.setAadharFile(aadharFile);

        Student student = service.registerStudent(dto);

        ApiResponseDTO<Student> response = new ApiResponseDTO<>(
                "success",
                "Student registered successfully",
                student
        );

        return ResponseEntity.ok(response);
    }


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDTO<List<StudentResponseDTO>>> getAllStudents() {
        List<StudentResponseDTO> students = service.getAllStudents();
        ApiResponseDTO<List<StudentResponseDTO>> response =
                new ApiResponseDTO<>("success", "All students fetched successfully", students);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/students/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDTO<String>> deleteStudent(@PathVariable String id) {
        service.deleteStudentById(id);
        return ResponseEntity.ok(
                new ApiResponseDTO<>("success", " Student deleted", null)
        );
    }




}
