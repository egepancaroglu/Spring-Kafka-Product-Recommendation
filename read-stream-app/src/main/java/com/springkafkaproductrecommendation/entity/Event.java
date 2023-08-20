package com.springkafkaproductrecommendation.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document(collection = "events")
public class Event {

    @Id
    private String id;
    private String event;
    private String messageid;
    private String userid;
    private ProductProperties properties;
    private Context context;
    private String timestamp;

}





