package com.example.jdbc_bulk;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Profile;
import javax.sql.DataSource;

@EnableRabbit

@Configuration
@Profile("cloud")
public class RabbitConfigurationCloud extends AbstractCloudConfig {

    @Bean
    public ConnectionFactory rabbitFactory() {
        return connectionFactory().rabbitConnectionFactory();
    }

    @Bean
    public DataSource dataSource() {
        return connectionFactory().dataSource();
    }

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
