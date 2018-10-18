package com.example.jdbc_bulk;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.*;

@RabbitListener (queues = "${spring.rabbitmq.queue}")
public class RabbitMessageReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMessageReceiver.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    private List<Object[]> acmsMessageGPList = new ArrayList<>();
    private int messageAcumulatorSize;
    // private int batch_size = Integer.parseInt("${app.batch_size}");
    private int batch_size = 5000;

    @RabbitHandler
    public void receiveMessage(LinkedHashMap inputMessage){

            JSONObject inputJSON = new JSONObject(inputMessage);
            int key = inputJSON.getInt("date");

            acmsMessageGPList.add(new Object[]{key, inputJSON.toString()});

            // System.out.println(inputJSON.toString());

            messageAcumulatorSize = acmsMessageGPList.size();

        if ( messageAcumulatorSize >= batch_size ) {
            LOGGER.info("Received batch of " + batch_size + " messages ");
            LOGGER.info("Starting batch insert.");
            jdbcTemplate.batchUpdate("INSERT INTO acms_messages (id, message_payload) VALUES (?,?) ", acmsMessageGPList);
            LOGGER.info("Batch insert completed.");
            acmsMessageGPList.clear();
        }
    }
}