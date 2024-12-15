package com.dummy.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListener {

    @org.springframework.kafka.annotation.KafkaListener(topics="123", groupId="group1")
    void listener(String data){
        log.info("Received message {}", data); //После получения сообщения в Кафку, пишем лог
    }
}
