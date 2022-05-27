package ru.school.foodapplicationgenerator.consumer;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.school.foodapplicationgenerator.entity.PersonalData;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

import static ru.school.foodapplicationgenerator.consumer.Constants.FOOD_DOCUMENT_TEMPLATE;

@Slf4j
@Component
public class FoodConsumer {

    @Value("${path.to.pdf}")
    private String pathToPdfDir;
    private static final Font FONT = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

    @RabbitListener(queues = "social_food")
    public void socialFoodListener(PersonalData personalData) throws FileNotFoundException, DocumentException {
        log.info("Social food got a message: {}", personalData);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pathToPdfDir + personalData.getLastName() + UUID.randomUUID() + "Food.pdf"));

        document.open();
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Paragraph(String.format(FOOD_DOCUMENT_TEMPLATE, personalData.getFirstName(),
                personalData.getMiddleName(), personalData.getLastName(), personalData.getAge()), FONT));
        document.add(paragraph);
        document.close();
    }
}
