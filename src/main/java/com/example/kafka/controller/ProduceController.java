package com.example.kafka.controller;

import com.google.gson.Gson;
import com.example.kafka.common.ErrorCode;
import com.example.kafka.common.MessageEntity;
import com.example.kafka.common.Response;
import com.example.kafka.producer.SampleProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/kafka")
public class ProduceController {
    @Autowired
    private SampleProducer sampleProducer;

    @Value("${kafka.topic.default}")
    private String topic;

    private Gson gson = new Gson();

    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json")
    public Response sendKafka() {
        return new Response(ErrorCode.SUCCESS, "OK");
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = "application/json")
    public Response sendKafka(@RequestBody MessageEntity message) {
        try {
            log.info("kafka's message = {}", gson.toJson(message));
            sampleProducer.send(topic, "key", message);
            log.info("sent kafka message successfully.");
            return new Response(ErrorCode.SUCCESS, "sent kafka message successfully");
        } catch (Exception e) {
            log.error("sent kafka message error", e);
            return new Response(ErrorCode.EXCEPTION, "sent kafka message error");
        }
    }
}
