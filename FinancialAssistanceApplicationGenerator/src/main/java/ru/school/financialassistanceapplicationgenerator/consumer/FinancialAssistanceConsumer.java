package ru.school.financialassistanceapplicationgenerator.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.school.financialassistanceapplicationgenerator.entity.PersonalData;

@Slf4j
@Component
public class FinancialAssistanceConsumer {

    @RabbitListener(queues = "financial_assistance")
    public void financialAssistanceListener(PersonalData personalData) {
        log.info("Financial assistance got a message: {}", personalData);
    }
}
