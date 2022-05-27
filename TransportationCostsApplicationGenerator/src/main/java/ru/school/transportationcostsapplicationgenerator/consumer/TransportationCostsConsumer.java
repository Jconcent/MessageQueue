package ru.school.transportationcostsapplicationgenerator.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.school.transportationcostsapplicationgenerator.entity.PersonalData;

@Slf4j
@Component
public class TransportationCostsConsumer {

    @RabbitListener(queues = "transportation_costs")
    public void transportationCostsListener(PersonalData personalData) {
        log.info("Transportation costs got a message: {}", personalData);
    }
}
