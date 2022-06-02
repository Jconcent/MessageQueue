package ru.school.studentsdataproducer.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.school.studentsdataproducer.entity.PersonalData;

@Slf4j
@AllArgsConstructor
@Controller
public class StudentsDataController {

    private static final String GRANT_EXCHANGE = "GRANT_EXCHANGE";
    private static final String SOCIAL_ASSISTANCE_EXCHANGE = "SOCIAL_ASSISTANCE_EXCHANGE";
    private static final String ROUTING_KEY = "grant.%d.%s";
    private static final String CONSENT = "consent";
    private static final String GRANT = "grant";
    private static final String GUARANTEE_LETTER = "guarantee.letter";

    private final RabbitTemplate rabbitTemplate;
    private final AmqpAdmin amqpAdmin;

    @GetMapping(path = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("personalData", new PersonalData());
        return "index";
    }

    @PostMapping("/studentsData")
    public String saveStudentsData(@ModelAttribute PersonalData personalData, Model model) {
        log.info("New data incoming: {}", personalData);
        rabbitTemplate.setExchange(SOCIAL_ASSISTANCE_EXCHANGE);
        rabbitTemplate.convertAndSend(personalData);
        rabbitTemplate.setExchange(GRANT_EXCHANGE);
        rabbitTemplate.convertAndSend(String.format(ROUTING_KEY, personalData.getCourse(), CONSENT), personalData);
        rabbitTemplate.convertAndSend(String.format(ROUTING_KEY, personalData.getCourse(), GRANT), personalData);
        rabbitTemplate.convertAndSend(String.format(ROUTING_KEY, personalData.getCourse(), GUARANTEE_LETTER), personalData);
        return "index";
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initMq() {
        amqpAdmin.initialize();
    }
}
