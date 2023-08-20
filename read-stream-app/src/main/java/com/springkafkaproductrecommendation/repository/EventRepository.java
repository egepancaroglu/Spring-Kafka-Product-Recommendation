package com.springkafkaproductrecommendation.repository;

import com.springkafkaproductrecommendation.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

}
