package com.springBoot.MyrPg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Entity
@Data   //getter,setter,toString ,equals , hashcode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String studentName;
    private String permanentAddress;
    private String aadharCardNumber;
    private String phoneNumber;
    private String gmail;
    private String currentAddress;

    private String aadharFileName;
    private String aadharFilePath;
}
