package com.springkafkaproductrecommendation.consumer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springkafkaproductrecommendation.entity.Event;
import com.springkafkaproductrecommendation.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaDatabaseConsumer {


    private final EventService eventService;

    @Autowired
    public KafkaDatabaseConsumer(EventService eventService) {
        this.eventService = eventService;
    }

    @KafkaListener(topics = "product-views", groupId = "myGroup")
    public void consumeMessage(String message) {
        System.out.println("Received Message in group myGroup: " + message);
        //Parse the incoming JSON and convert it to an Event object
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Event event = objectMapper.readValue(message, Event.class);
            eventService.saveEvent(event);
            System.out.println("Event saved to MongoDB: " + event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}