package com.springkafkaproductrecommendation.entity;


import lombok.Getter;
import lombok.Setter;
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
    private Properties properties;
    private Context context;
    private String timestamp;
}

@Getter
@Setter
class Properties {
    private String productid;
}

@Getter
@Setter
class Context {
    private String source;

}
