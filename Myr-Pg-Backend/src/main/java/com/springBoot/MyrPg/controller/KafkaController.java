package com.springBoot.MyrPg.controller;

import com.springBoot.MyrPg.DTO.ApiResponseDTO;
import com.springBoot.MyrPg.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaProducerService kafkaProducerService;

    @PostMapping("/send-message")
    public ResponseEntity<ApiResponseDTO<String>> sendCustomMessage(@RequestParam String message) {
        kafkaProducerService.sendNewStudentNotification(message);
        return ResponseEntity.ok(new ApiResponseDTO<>("success", "Kafka message sent!", message));
    }
}
