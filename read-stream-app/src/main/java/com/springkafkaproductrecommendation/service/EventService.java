package com.springkafkaproductrecommendation.service;

import com.springkafkaproductrecommendation.entity.Event;
import com.springkafkaproductrecommendation.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

}
