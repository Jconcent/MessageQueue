package ru.school.studentsdataproducer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class ApplicationConfig {

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setMessageConverter(jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public Queue socialFoodQueue() {
        return new Queue("social_food");
    }

    @Bean
    public Queue financialAssistanceQueue() {
        return new Queue("financial_assistance");
    }

    @Bean
    public Queue transportationCostsQueue() {
        return new Queue("transportation_costs");
    }

    @Bean
    public Queue grantOtherDocumentsQueue() {
        return new Queue("grant_other_documents");
    }

    @Bean
    public Queue grantContractsQueue() {
        return new Queue("grant_contracts");
    }

    @Bean
    public FanoutExchange socialAssistanceExchange() {
        return new FanoutExchange("SOCIAL_ASSISTANCE_EXCHANGE");
    }

    @Bean
    public TopicExchange grantExchange() {
        return new TopicExchange("GRANT_EXCHANGE");
    }

    @Bean
    public Binding socialFoodBinding() {
        return BindingBuilder.bind(socialFoodQueue()).to(socialAssistanceExchange());
    }

    @Bean
    public Binding financialAssistanceBinding() {
        return BindingBuilder.bind(financialAssistanceQueue()).to(socialAssistanceExchange());
    }

    @Bean
    public Binding transportationCostsBinding() {
        return BindingBuilder.bind(transportationCostsQueue()).to(socialAssistanceExchange());
    }

    @Bean
    public Binding grantContractsBinding() {
        return BindingBuilder.bind(grantContractsQueue()).to(grantExchange()).with("grant.1.*");
    }

    @Bean
    public Binding grantOtherDocumentsBinding() {
        return BindingBuilder.bind(grantOtherDocumentsQueue()).to(grantExchange()).with("grant.#");
    }
}
