package com.springBoot.MyrPg.config;

import com.springBoot.MyrPg.kafkaTopic.AppConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic newStudentTopic(){
        return TopicBuilder
                .name(AppConstant.NEW_STUDENT_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    //bin\windows\kafka-server-start.bat config\kraft\server.properties
}
