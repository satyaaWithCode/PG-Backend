package com.springBoot.MyrPg.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuilderResponseDTO<T> {

    private String timestamp;
    private int status;
    private String error;
    private String message;
    private T data; // can be null in case of error
}
