package ru.school.guaranteelettergenerator.consumer;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.school.guaranteelettergenerator.entity.PersonalData;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import static ru.school.guaranteelettergenerator.consumer.Constants.GUARANTEE_LETTER_DOCUMENT_TEMPLATE;

@Slf4j
@Component
public class GuaranteeLetterConsumer {

    @Value("${path.to.pdf}")
    private String pathToPdfDir;
    private static final Font FONT = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

    @RabbitListener(queues = "grant_other_documents")
    public void guaranteeLetterListener(PersonalData personalData) throws FileNotFoundException, DocumentException {
        log.info("Guarantee letter generator got a message: {}", personalData);

        Date now = new Date();
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(String.format("%s%s%s%sGuaranteeLetter.pdf", pathToPdfDir,
                personalData.getFirstName(), personalData.getMiddleName(), personalData.getLastName())));

        document.open();
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Paragraph(String.format(GUARANTEE_LETTER_DOCUMENT_TEMPLATE, personalData.getFirstName(),
                personalData.getMiddleName(), personalData.getLastName(), now), FONT));
        document.add(paragraph);
        document.close();
    }
}
