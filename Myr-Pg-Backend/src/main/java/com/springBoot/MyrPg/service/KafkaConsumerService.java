package com.springBoot.MyrPg.service;

public interface KafkaConsumerService {

    void consumeNewStudentNotification(String message);
}
