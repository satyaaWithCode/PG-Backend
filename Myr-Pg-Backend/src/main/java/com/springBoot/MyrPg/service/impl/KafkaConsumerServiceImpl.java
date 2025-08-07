package com.springBoot.MyrPg.service.impl;

import com.springBoot.MyrPg.kafkaTopic.AppConstant;
import com.springBoot.MyrPg.service.KafkaConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    @KafkaListener(topics =AppConstant.NEW_STUDENT_TOPIC, groupId =AppConstant.STUDENT_GROUP_ID)

    public void consumeNewStudentNotification(String message) {
        System.out.println(" Kafka message received: " + message);

        // Forward to WebSocket topic for admins
        messagingTemplate.convertAndSend("/topic/students", message);
    }
}
