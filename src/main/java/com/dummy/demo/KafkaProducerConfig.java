package com.dummy.demo;


import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration

public class KafkaProducerConfig {

    Map<String, Object> props = new HashMap<>(); //Тут  набор свойств




    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapservers;



    public void createProducerFactory() {
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapservers); //Адреса серверов
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); //Тип сериализации кл к строке
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); //Тип сериализации к JSON
//        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public ProducerFactory<String, PatternClass> producerFactory(){
        createProducerFactory();
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public ProducerFactory<String, PatternGet> producerFactoryGet(){
        createProducerFactory();
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, PatternClass> kafkaTemplate(){
        return new KafkaTemplate<>( producerFactory());
    }

    @Bean
    public KafkaTemplate<String, PatternGet> kafkaTemplateGet(){
        return new KafkaTemplate<>(producerFactoryGet());
    }
}
