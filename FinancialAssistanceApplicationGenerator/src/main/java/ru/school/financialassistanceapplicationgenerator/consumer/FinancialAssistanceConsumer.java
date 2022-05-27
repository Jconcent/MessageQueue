package ru.school.financialassistanceapplicationgenerator.consumer;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.school.financialassistanceapplicationgenerator.entity.PersonalData;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

import static ru.school.financialassistanceapplicationgenerator.consumer.Constants.FINANCIAL_ASSISTANCE_DOCUMENT_TEMPLATE;

@Slf4j
@Component
public class FinancialAssistanceConsumer {

    @Value("${path.to.pdf}")
    private String pathToPdfDir;
    private static final Font FONT = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

    @RabbitListener(queues = "financial_assistance")
    public void financialAssistanceListener(PersonalData personalData) throws FileNotFoundException, DocumentException {
        log.info("Financial assistance got a message: {}", personalData);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pathToPdfDir + personalData.getLastName() + UUID.randomUUID() + "FinancialAssistance.pdf"));

        document.open();
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Paragraph(String.format(FINANCIAL_ASSISTANCE_DOCUMENT_TEMPLATE, personalData.getFirstName(),
                personalData.getMiddleName(), personalData.getLastName(), personalData.getAge()), FONT));
        document.add(paragraph);
        document.close();
    }
}
