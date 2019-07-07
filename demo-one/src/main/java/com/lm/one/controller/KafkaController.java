package com.lm.one.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@Slf4j
@RestController
@RequestMapping("kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("producer")
    public String testKafka () {
        IntStream.range(1,100).forEach(i ->{
            kafkaTemplate.send("test" , "key" + i , "data" + i);
        });
        return "success";
    }

    @KafkaListener(topics = "test")
    public void receive(ConsumerRecord<?, ?> consumer) {
        log.info("{} - {}:{}", consumer.topic(), consumer.key(), consumer.value());
    }
}
