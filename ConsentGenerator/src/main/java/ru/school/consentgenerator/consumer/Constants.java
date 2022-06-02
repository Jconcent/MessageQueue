package ru.school.consentgenerator.consumer;

public final class Constants {

    public static final String CONSENT_DOCUMENT_TEMPLATE = "Autonomous non-profit organization.\n\n" +
            "I'm %s %s %s give my consent to the processing of my personal data.\n\n" +
            "The date: %s\n\n" +
            "Personal data:\n" +
            "First Name: %s\n" +
            "Middle Name: %s\n" +
            "Last Name: %s\n" +
            "Age: %s\n" +
            "Gender: %s\n" +
            "Place of residence: %s\n" +
            "Phone number: %s\n";

    private Constants() {

    }
}
