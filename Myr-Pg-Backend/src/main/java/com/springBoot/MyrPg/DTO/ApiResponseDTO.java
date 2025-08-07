package com.springBoot.MyrPg.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseDTO<T> {

    private String status;
    private String message;
    private T data;
}
