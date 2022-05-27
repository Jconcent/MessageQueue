package ru.school.studentsdataproducer.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    private final RabbitTemplate rabbitTemplate;
    private final AmqpAdmin amqpAdmin;

    @GetMapping(path = {"/", "/index"})
    public String index(Model model) {
        amqpAdmin.initialize();
        model.addAttribute("personalData", new PersonalData());
        return "index";
    }

    @PostMapping("/studentsData")
    public String saveStudentsData(@ModelAttribute PersonalData personalData, Model model) {
        log.info("New data incoming: {}", personalData);
        rabbitTemplate.convertAndSend(personalData);
        return "index";
    }
}
