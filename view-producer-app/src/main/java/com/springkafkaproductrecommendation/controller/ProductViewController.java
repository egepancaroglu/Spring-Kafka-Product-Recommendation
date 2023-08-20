package com.springkafkaproductrecommendation.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
public class ProductViewController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${file.path}")
    private String filePath;

    @Value("${kafka.topic}")
    private String topic;

    @PostMapping("/sendProductViews")
    public String sendProductViews() throws InterruptedException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                JsonObject jsonObject = new Gson().fromJson(line, JsonObject.class);
//              long timestamp = System.currentTimeMillis(); // get current timestamp for unix system
                LocalDateTime localDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                String formattedDateTime = localDateTime.format(formatter);
                jsonObject.addProperty("timestamp", formattedDateTime);
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, jsonObject.toString());
                kafkaTemplate.send(record);
                System.out.println(record);
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error sending product views";
        }
        return "Product views sent successfully";
    }
}















