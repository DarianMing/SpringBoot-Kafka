package com.lm.three.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    //构造消费者属性map，ConsumerConfig中的可配置属性比spring boot自动配置要多
    private Map<String, Object> consumerProperties(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, Contants.GROUP_ID_REPORT);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Contants.BOOTSTRAP_SERVERS);
        return props;
    }

    @Bean("consumerFactory")
    public DefaultKafkaConsumerFactory consumerFactory () {
        return new DefaultKafkaConsumerFactory(consumerProperties());
    }

    //个性化定义消费者
    @Bean("listenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String,String> listenerContainerFactory (
            DefaultKafkaConsumerFactory<String,String> consumerFactory ) {
        ConcurrentKafkaListenerContainerFactory<String,String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        //指定使用DefaultKafkaConsumerFactory
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }


    //创建生产者配置map，ProducerConfig中的可配置属性比spring boot自动配置要多
    private Map<String, Object> producerProperties(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Contants.BOOTSTRAP_SERVERS);
        return props;
    }

    /**
     * 不使用spring boot的KafkaAutoConfiguration默认方式创建的DefaultKafkaProducerFactory，重新定义
     * @return
     */
    @Bean("produceFactory")
    public DefaultKafkaProducerFactory<String,String> produceFactory(){
        return new DefaultKafkaProducerFactory<>(producerProperties());
    }


    /**
     * 不使用spring boot的KafkaAutoConfiguration默认方式创建的KafkaTemplate，重新定义
     * @param produceFactory
     * @return
     */
    @Bean
    public KafkaTemplate<String,String> kafkaTemplate(DefaultKafkaProducerFactory<String,String> produceFactory){
        return new KafkaTemplate<>(produceFactory);
    }


}
