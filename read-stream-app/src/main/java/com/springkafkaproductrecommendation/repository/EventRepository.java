package com.springkafkaproductrecommendation.repository;

import com.springkafkaproductrecommendation.entity.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findByUseridAndEventOrderByTimestampDesc(String userId, String event, Pageable pageable);

}
