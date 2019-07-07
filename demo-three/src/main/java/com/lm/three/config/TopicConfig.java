package com.lm.three.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TopicConfig {

    @Bean
    public KafkaAdmin kafkaAdmin () {
        Map<String,Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG , Contants.BOOTSTRAP_SERVERS);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic pageTopic () {
        return new NewTopic(Contants.TOPIC_PAGE , 10 , (short)2);
    }

    @Bean
    public NewTopic clickTopic () {
        return new NewTopic(Contants.TOPIC_CLICK , 10 , (short)2 );
    }

}
