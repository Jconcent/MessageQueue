package ru.school.transportationcostsapplicationgenerator.consumer;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.school.transportationcostsapplicationgenerator.entity.PersonalData;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.UUID;

import static ru.school.transportationcostsapplicationgenerator.consumer.Constants.TRANSPORTATION_DOCUMENT_TEMPLATE;

@Slf4j
@Component
public class TransportationCostsConsumer {

    @Value("${path.to.pdf}")
    private String pathToPdfDir;
    private static final Font FONT = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

    @RabbitListener(queues = "transportation_costs")
    public void transportationCostsListener(PersonalData personalData) throws FileNotFoundException, DocumentException {
        log.info("Transportation costs got a message: {}", personalData);

        Date now = new Date();
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(String.format("%s%s%s%sTransportationCosts.pdf", pathToPdfDir,
                personalData.getFirstName(), personalData.getMiddleName(), personalData.getLastName())));

        document.open();
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Paragraph(String.format(TRANSPORTATION_DOCUMENT_TEMPLATE, personalData.getFirstName(),
                personalData.getMiddleName(), personalData.getLastName(), personalData.getCourse(), personalData.getAge(),
                personalData.isFromLowIncome() ? 'Y' : 'N', now), FONT));
        document.add(paragraph);
        document.close();
    }
}
