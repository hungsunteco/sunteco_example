/*
 *  Copyright (C) 2021 Sunteco, Inc.
 *
 *  Sunteco License Notice
 *
 *  The contents of this file are subject to the Sunteco License
 *  Version 1.0 (the "License"). You may not use this file except in
 *  compliance with the License. The Initial Developer of the Original
 *  Code is Sunteco, JSC. Portions Copyright 2021 Sunteco JSC
 *
 *  All Rights Reserved.
 */

package com.sunteco.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

public class SimpleProducer {
    public static void main(String[] args) {
        String bootstrapServers = "localhost:9092";
        String ClientID = "myClient";
        String topicName = "myTopic";

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, ClientID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");

        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(props);
        KafkaTemplate kafkaTemplate = new KafkaTemplate<>(producerFactory);

        for(int i = 0; i < 10; i++) {
            kafkaTemplate.send(topicName, "Message " + i);
        }

        kafkaTemplate.destroy();
    }

}
