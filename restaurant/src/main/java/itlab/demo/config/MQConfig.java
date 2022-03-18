package itlab.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    public static final String QUEUE_LOW = "message_low_queue";
    public static final String EXCHANGE_LOW = "message_low_exchange";
    public static final String ROUTING_KEY_LOW = "message_low_routingKey";
    public static final String QUEUE_UP = "message_up_queue";
    public static final String EXCHANGE_UP = "message_up_exchange";
    public static final String ROUTING_KEY_UP = "message_up_routingKey";

    @Bean
    public Queue queueLow() {
        return  new Queue(QUEUE_LOW);
    }

    @Bean
    public Queue queueUp() {
        return  new Queue(QUEUE_UP);
    }

    @Bean
    public TopicExchange exchangeLow() {
        return new TopicExchange(EXCHANGE_LOW);
    }

    @Bean
    public TopicExchange exchangeUp() {
        return new TopicExchange(EXCHANGE_UP);
    }

    @Bean
    public Binding bindingLow(Queue queueLow, TopicExchange exchangeLow) {
        return BindingBuilder
                .bind(queueLow)
                .to(exchangeLow)
                .with(ROUTING_KEY_LOW);
    }

    @Bean
    public Binding binding(Queue queueUp, TopicExchange exchangeUp) {
        return BindingBuilder
                .bind(queueUp)
                .to(exchangeUp)
                .with(ROUTING_KEY_UP);
    }

    @Bean
    public MessageConverter messageConverter() {
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return  template;
    }
}