package com.springkafkaproductrecommendation.controller;

import com.springkafkaproductrecommendation.entity.Event;
import com.springkafkaproductrecommendation.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final EventService eventService;

    public ProductController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/user/last-10/{user-id}")
    public ResponseEntity<List<Event>> getLast10ProductViewsByUserId(@PathVariable("user-id") String userId) {
        List<Event> productViews = eventService.getLast10ProductViewsByUserId(userId);
        return ResponseEntity.ok(productViews);
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/products")
    public Set<String> getProducts() {
        return eventService.getProducts();
    }
}

