package com.springkafkaproductrecommendation.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic}")
    private String productViewsTopic;

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name("product-views")
                .build();
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(org.springframework.kafka.core.ProducerFactory<String, String> pf) {
        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(pf);
        kafkaTemplate.setDefaultTopic(productViewsTopic);
        return kafkaTemplate;
    }
}



