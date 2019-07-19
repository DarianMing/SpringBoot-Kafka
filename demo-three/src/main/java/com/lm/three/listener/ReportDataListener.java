package com.lm.three.listener;

import com.lm.three.config.Contants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReportDataListener {

    @KafkaListener(id = Contants.REPORT_DATA_CONTAINER ,
            topics = {Contants.TOPIC_PAGE , Contants.TOPIC_CLICK})
    public void listen (ConsumerRecord<? , ?> record) {
        log.info("listen监听到消息记录=================");
        log.info("topic= {} "  , record.topic());
        log.info("offset= {}" , record.offset());
        log.info("partition = " , record.partition());
        log.info("key= {}" , record.key());
        log.info("value= {}" , record.value());
    }

//    @KafkaListener(id = Contants.REPORT_DATA_CONTAINER ,
//            topics = {Contants.TOPIC_PAGE , Contants.TOPIC_CLICK})
//    public void listenSameGroup (ConsumerRecord<? , ?> record) {
//        log.info("listenSameGroup监听到消息记录=================");
//        log.info("topic= {} "  , record.topic());
//        log.info("offset= {}" , record.offset());
//        log.info("partition = " , record.partition());
//        log.info("key= {}" , record.key());
//        log.info("value= {}" , record.value());
//    }

    @KafkaListener(topics = {Contants.TOPIC_PAGE , Contants.TOPIC_CLICK})
    public void unSameGroup (ConsumerRecord<? , ?> record) {
        log.info("unSameGroup监听到消息记录=================");
        log.info("topic= {} "  , record.topic());
        log.info("offset= {}" , record.offset());
        log.info("partition = " , record.partition());
        log.info("key= {}" , record.key());
        log.info("value= {}" , record.value());
    }

}
