package com.springBoot.MyrPg.service.impl;

import com.springBoot.MyrPg.kafkaTopic.AppConstant;
import com.springBoot.MyrPg.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;


    @Override
    public void sendNewStudentNotification(String message) { //send message to topic //producer produce message
        kafkaTemplate.send(AppConstant.NEW_STUDENT_TOPIC, message);
    }
}
