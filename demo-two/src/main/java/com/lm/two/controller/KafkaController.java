package com.lm.two.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
            kafkaTemplate.send("test" , "key-two" + i , "data" + i);
        });
        return "success";
    }

    /**
     * 消费者配置配置了一个bean，@Bean(“listenerContainerFactory”)，这个bean可以指定为消费者，注解方式中是如下的使用方式。
     * containerFactory = "listenerContainerFactory"指定了使用listenerContainerFactory作为消费者。
     * 注意registryReceiver中的参数，ConsumerRecord对比之前的消费者，因为设置listenerContainerFactory是批量消费，
     *      因此ConsumerRecord是一个List，如果不是批量消费的话，相对应就是一个对象。
     * 注意第二个参数Acknowledgment，这个参数只有在设置消费者的ack应答模式为AckMode.MANUAL_IMMEDIATE才能注入，意思是需要手动ack。
     *
     * listenerContainerFactory设置了批量拉取消息，因此参数是List<ConsumerRecord<Integer, String>>，否则是ConsumerRecord
     * @param integerStringConsumerRecords
     * @param acknowledgment
     */
    @KafkaListener(topics = "test" , containerFactory = "listenerContainerFactory")
    public void receive(List<ConsumerRecord<?, ?>> consumerList , Acknowledgment acknowledgment) {
        consumerList.forEach(consumer ->{
            //业务处理逻辑
            log.info("partition= {} , offset= {} , key= {} , value= {}" , consumer.partition() , consumer.offset() ,
                    consumer.key() , consumer.value());
            acknowledgment.acknowledge();
        });
    }
}
