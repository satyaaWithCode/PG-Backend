
package com.springBoot.MyrPg.service.impl;
import com.springBoot.MyrPg.DTO.StudentRequestDTO;
import com.springBoot.MyrPg.DTO.StudentResponseDTO;
import com.springBoot.MyrPg.entity.Student;
import com.springBoot.MyrPg.repository.StudentRepository;
import com.springBoot.MyrPg.service.KafkaProducerService;
import com.springBoot.MyrPg.service.StudentService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Builder
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    private final KafkaProducerService kafkaProducerService;

    @Override
    public Student registerStudent(StudentRequestDTO dto) throws IOException {

        //create aaddhar upload file path

        MultipartFile file = dto.getAadharFile();

        String folderPath = System.getProperty("user.dir") + File.separator + "uploads";
        File uploadDir = new File(folderPath);
        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs();
            if (!created) {
                throw new RuntimeException(" Failed to create upload directory: " + folderPath);
            }
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File dest = new File(uploadDir, fileName);

        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException(" Failed to save file: " + fileName, e);
        }

        //upload

        Student student = Student.builder()
                .studentName(dto.getStudentName())
                .permanentAddress(dto.getPermanentAddress())
                .aadharCardNumber(dto.getAadharCardNumber())
                .phoneNumber(dto.getPhoneNumber())
                .gmail(dto.getGmail())
                .currentAddress(dto.getCurrentAddress())
                .aadharFileName(fileName)
                .aadharFilePath(dest.getAbsolutePath())
                .build();

        Student savedStudent = repository.save(student);

        //  kafka for this block send notification

        long totalStudents = repository.count();
        String message = String.format(
                " New student added: %s. Total students: %d",
                savedStudent.getStudentName(),
                totalStudents
        );
        kafkaProducerService.sendNewStudentNotification(message);

        return savedStudent;
    }

    //for admin

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        return repository.findAll().stream()
                .map(student -> StudentResponseDTO.builder()
                        .id(student.getId())
                        .studentName(student.getStudentName())
                        .permanentAddress(student.getPermanentAddress())
                        .aadharCardNumber(student.getAadharCardNumber())
                        .phoneNumber(student.getPhoneNumber())
                        .gmail(student.getGmail())
                        .currentAddress(student.getCurrentAddress())
                        .aadharFileName(student.getAadharFileName())
                        .aadharFile(student.getAadharFileName())
                        .build())
                .toList();
    }

    @Override
    public void deleteStudentById(String id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        repository.deleteById(id);
        
    }
}
