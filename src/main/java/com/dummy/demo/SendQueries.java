package com.dummy.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping
public class SendQueries {
    @RequestMapping(value="/create_book", method = RequestMethod.POST)
    public ResponseEntity<String> method(@RequestBody PatternClass requestObject) throws IOException{
        send(requestObject);

        return ResponseEntity.status(200).body("Message received successfully"); //Возвращаем 200 статус-код и сообщение, что всё прошло ок
    }

    public ResponseEntity<String> onlyAnswer(@RequestBody PatternClass requestObject) throws IOException{

        return ResponseEntity.status(200).body("Message received successfully"); //Возвращаем 200 статус-код и сообщение, что всё прошло ок
    }

    @Autowired
    public KafkaTemplate<String, PatternClass> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, PatternGet> kafkaJokeTemplate;

    public void send(PatternClass pt){
        kafkaTemplate.send("123", pt); //Отправка сообщения в топик 123
    }



    @GetMapping("/get_joke")
    public ResponseEntity<String> getJoke() {
        PatternGet response = new PatternGet();
        response.setJoke(JokeParser.parseJoke());
        response.setDate(LocalDate.now());
        kafkaJokeTemplate.send("123", response);

        return ResponseEntity.status(200).body("Success"); // Возвращаем 200 статус-код с сообщением
    }



}


