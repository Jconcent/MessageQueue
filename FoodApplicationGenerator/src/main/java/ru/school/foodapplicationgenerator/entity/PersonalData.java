package ru.school.foodapplicationgenerator.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonalData implements Serializable {
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer course;
    private Integer age;
    private String gender;
    private String phoneNumber;
    private String placeOfResidence;
    private boolean fromLowIncome;
}
