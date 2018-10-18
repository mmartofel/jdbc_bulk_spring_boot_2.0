package com.example.jdbc_bulk;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@EnableRabbit

@Configuration
@Profile("default")
public class RabbitConfigurationLocal {

    @Bean
    public RabbitMessageReceiver rabbitMessageReceiver() {
        System.out.println("Bean rabbitMessageReceiver instantiated.");
        return new RabbitMessageReceiver();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        System.out.println("Bean jsonMessageConverter instantiated.");
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

}
