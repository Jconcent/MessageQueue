package ru.school.foodapplicationgenerator.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.school.foodapplicationgenerator.entity.PersonalData;

@Slf4j
@Component
public class FoodConsumer {

    @RabbitListener(queues = "social_food")
    public void socialFoodListener(PersonalData personalData) {
        log.info("Social food got a message: {}", personalData);
    }
}
