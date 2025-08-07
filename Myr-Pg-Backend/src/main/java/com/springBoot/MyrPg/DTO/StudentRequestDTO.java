package com.springBoot.MyrPg.DTO;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class StudentRequestDTO {
//for all user
    private String id;
    private String studentName;
    private String permanentAddress;
    private String aadharCardNumber;
    private String phoneNumber;
    private String gmail;
    private String currentAddress;
    private String aadharFileName;
    private MultipartFile aadharFile;
}
