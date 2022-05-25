package ru.school.studentsdataproducer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.school.studentsdataproducer.entity.PersonalData;

@Controller
public class StudentsDataController {

    @GetMapping(path = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("personalData", new PersonalData());
        return "index";
    }

    @PostMapping("/studentsData")
    public String saveStudentsData(@ModelAttribute PersonalData personalData, Model model) {
        System.out.println(personalData.toString());
        return "index";
    }
}
