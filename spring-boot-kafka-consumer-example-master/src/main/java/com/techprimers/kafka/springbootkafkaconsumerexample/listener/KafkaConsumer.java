package com.techprimers.kafka.springbootkafkaconsumerexample.listener;

import com.techprimers.kafka.springbootkafkaconsumerexample.model.User;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class KafkaConsumer {

    @KafkaListener(topics = "QLNV", groupId = "group_id")
    

    @RefreshScope
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }


    @KafkaListener(topics = "Kafka_Example_json", groupId = "group_json",
            containerFactory = "userKafkaListenerFactory")

    @RefreshScope
    public void consumeJson(User user) {
        System.out.println("Consumed JSON Message: " + user);
    }
}
