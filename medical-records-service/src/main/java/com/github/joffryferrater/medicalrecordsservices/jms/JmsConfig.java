package com.github.joffryferrater.medicalrecordsservices.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * Created by joffer on 1/3/2019
 */
@EnableJms
@Configuration
public class JmsConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Value("${spring.activemq.user}")
    private String user;

    @Value("${spring.activemq.password}")
    private String password;

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter jmsConverter = new MappingJackson2MessageConverter();
        jmsConverter.setTargetType(MessageType.TEXT);
        jmsConverter.setTypeIdPropertyName("_type");
        return jmsConverter;
    }

    @Bean
    public CachingConnectionFactory connectionFactory() {
        final ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(user, password,
            brokerUrl);
        CachingConnectionFactory factory = new CachingConnectionFactory(activeMQConnectionFactory);
        factory.setClientId("medical-records-service");
        factory.setSessionCacheSize(100);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
        jmsTemplate.setDeliveryPersistent(true);
        jmsTemplate.setSessionTransacted(true);
        return jmsTemplate;
    }
}
