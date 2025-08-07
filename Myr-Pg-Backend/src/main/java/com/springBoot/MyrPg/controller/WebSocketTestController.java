package com.springBoot.MyrPg.controller;


import com.springBoot.MyrPg.DTO.ApiResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ws")
@RequiredArgsConstructor
public class WebSocketTestController {

    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping("/send")
    public ResponseEntity<ApiResponseDTO<String>> sendMessageToWebSocket(@RequestParam String message) {
        messagingTemplate.convertAndSend("/topic/students", message);

        ApiResponseDTO<String> response = new ApiResponseDTO<>(
                "success",
                " Message sent via WebSocket",
                message
        );

        return ResponseEntity.ok(response);
    }

}
