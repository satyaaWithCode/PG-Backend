package com.springBoot.MyrPg.service;

public interface KafkaProducerService {

    void sendNewStudentNotification(String message);
}
