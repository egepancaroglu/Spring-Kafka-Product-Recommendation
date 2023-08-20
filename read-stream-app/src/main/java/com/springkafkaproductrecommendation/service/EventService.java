package com.springkafkaproductrecommendation.service;

import com.springkafkaproductrecommendation.entity.Event;
import com.springkafkaproductrecommendation.entity.ProductProperties;
import com.springkafkaproductrecommendation.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EventService {


    private MongoTemplate mongoTemplate;

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getLast10ProductViewsByUserId(String userId) {
        return eventRepository.findByUseridAndEventOrderByTimestampDesc(userId, "ProductView", PageRequest.of(0, 10));
    }

    public Set<String> getProducts() {
        List<Event> events = eventRepository.findAll();
        Set<String> productIds = new HashSet<>();

        for (Event event : events) {
            ProductProperties properties = event.getProperties();
            if (properties != null) {
                String productId = properties.getProductid();
                if (productId != null) {
                    productIds.add(productId);
                }
            }
        }

        return productIds;
    }

}


