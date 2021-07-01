package at.htlgkr.htltestet.pdf;

import at.htlgkr.htltestet.barcode.BarcodeController;
import at.htlgkr.htltestet.data.RegistrationData;
import at.htlgkr.htltestet.data.RegistrationDataRepository;
import at.htlgkr.htltestet.pdf.Line;
import at.htlgkr.htltestet.pdf.PDFData;
import lombok.Data;
import org.apache.pdfbox.cos.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.annotation.*;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Data
public class RegistrationPDF extends PDFData {
    private String screeningStation;
    private String code;

    @Override
    public byte[] createPDF() throws Exception {
        byte[] barcode = BarcodeController.getBarcodeStatic(code, 50);
        PDDocument registration = new PDDocument();
        PDPage page = new PDPage();
        registration.addPage(page);
        PDAcroForm acro = new PDAcroForm(registration);
        registration.getDocumentCatalog().setAcroForm(acro);

        PDPageContentStream pdcs = new PDPageContentStream(registration, registration.getPage(0));

        BufferedImage bi = ImageIO.read(new File("src\\main\\resources\\Images\\Logo_HTLBA_Grieskirchen.png"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "png", baos);
        byte[] bytes = baos.toByteArray();
        PDImageXObject pdImage = PDImageXObject.createFromByteArray(registration, bytes, "");
        pdcs.drawImage(pdImage, 30, 700);

        pdImage = PDImageXObject.createFromByteArray(registration, barcode, "");


        pdcs.drawImage(pdImage, 385, 700);

        addTextField(100, 555, 150, 15, registration);
        addTextField(430, 555, 150, 15, registration);
        addTextField(100, 540, 150, 15, registration);
        addTextField(430, 540, 150, 15, registration);
        addTextField(100, 525, 150, 15, registration);
        addTextField(430, 525, 150, 15, registration);
        addTextField(100, 510, 150, 15, registration);
        addTextField(430, 510, 150, 15, registration);
        addTextField(100, 480, 150, 30, registration);
        addTextField(430, 480, 150, 30, registration);

        addCheckbox(30, 455, 10, 10, registration);
        addCheckbox(30, 398, 10, 10, registration);

        addCheckbox(60, 105, 20, 20, registration);
        addCheckbox(250, 105, 20, 20, registration);
        addCheckbox(450, 105, 20, 20, registration);

        pdcs.addRect(385, 640, 200, 120);
        pdcs.setStrokingColor(Color.BLACK);
        pdcs.stroke();
        pdcs.addRect(385, 640, 200, 31);
        pdcs.setStrokingColor(Color.BLACK);
        pdcs.stroke();

        pdcs.addRect(385, 160, 200, 80);
        pdcs.setStrokingColor(Color.BLACK);
        pdcs.stroke();

        pdcs.beginText();
        pdcs.setLeading(14.5f);
        pdcs.newLineAtOffset(30, 690);
        for(int i = 0; i < 57-code.length(); ++i)
        {
            code = " " + code;
        }

        writeLine(PDType1Font.HELVETICA_BOLD, 18, Color.BLACK, true, pdcs, "Aktion \"HTL testet\"");
        writeLine(PDType1Font.HELVETICA, 18, Color.BLACK,false, pdcs, code);
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,true, pdcs, "                                                                                                                                                             Fortlaufende Laufzettel-Nummer");
        writeLine(PDType1Font.HELVETICA_BOLD, 16, Color.BLACK, true, pdcs, "Einwilligungserklärung Antigen-Test");
        writeLine(PDType1Font.HELVETICA_BOLD, 18, Color.BLACK,true, pdcs, "");
        writeLine(PDType1Font.HELVETICA_BOLD, 16, Color.BLACK,true, pdcs, "SARS-CoV-2 / Covid-19");
        writeLine(PDType1Font.HELVETICA_BOLD, 18, Color.BLACK,true, pdcs, "");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,true, pdcs, "Zu testende Person");
        writeLine(PDType1Font.HELVETICA_BOLD, 18, Color.BLACK,true, pdcs, "");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,true, pdcs, "               Name:                                                                                                            Geburtsdatum:");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,true, pdcs, "                                                                                                                                          Geschlecht:");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,true, pdcs, "          Vorname:                                                                                             SV-Nummer (10-stellig):");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,true, pdcs, "           PLZ, Ort:                                                                                                  Mobiltelefonnummer:");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,true, pdcs, "              Straße,                                                                                                          E-Mail-Adresse:");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,true, pdcs, "   Hausnummer:");
        writeLine(PDType1Font.HELVETICA_BOLD, 18, Color.BLACK,true, pdcs, "");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,true, pdcs, "          Hiermit erkläre ich ausdrücklich meine Zustimmung zur elektronischen Erfassung und Weiterverarbeitung meiner Daten sowie");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,true, pdcs, "          die freiwillige Durchführung eines Testabstrichs zur Durchführung eines Antigen-Schnelltests auf SARS-CoV-2 / Covid-19");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,true, pdcs, "          (gemäß Informationen und Datenschutzerklärung auf");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLUE,false, pdcs, " www.österreich-testet.gv.at/datenschutz");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,false, pdcs, ")");
        writeLine(PDType1Font.HELVETICA_BOLD, 18, Color.BLACK,true, pdcs, "");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,true, pdcs, "          Hiermit erteile ich die Einwilligung und Zustimmung zur Durchführung der Probennahme als Elternteil, Obsorgeberechtigte(r),");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,true, pdcs, "          Erwachsenenvertreterin/Erwachsenenvertreter");
        writeLine(PDType1Font.HELVETICA_BOLD, 18, Color.BLACK,true, pdcs, "");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,true, pdcs, "          Vorname Nachname   ______________________________________________________   geboren am   _____________________");
        writeLine(PDType1Font.HELVETICA_BOLD, 18, Color.BLACK,true, pdcs, "");
        writeLine(PDType1Font.HELVETICA_BOLD, 18, Color.BLACK,true, pdcs, "");
        writeLine(PDType1Font.HELVETICA_BOLD, 9, Color.RED,true, pdcs, "      Bitte bringen Sie einen amtlichen Lichtbildausweis zur");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,false, pdcs, "                      __________________________________________________");
        writeLine(PDType1Font.HELVETICA_BOLD, 9, Color.RED,true, pdcs, "                                        Testung mit.");
        writeLine(PDType1Font.HELVETICA_BOLD, 9, Color.BLACK,false, pdcs, "                                                                                                                              Datum, Unterschrift");
        writeLine(PDType1Font.HELVETICA_BOLD, 18, Color.BLACK,true, pdcs, "");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,true, pdcs, "_______________________________________________________________________________________________________________");
        writeLine(PDType1Font.HELVETICA_BOLD, 9, Color.RED,true, pdcs, "                                     BEREICH ZUM BESCHRIFTEN und AUSFÜLLEN durch die SCREENING-STATION!");
        writeLine(PDType1Font.HELVETICA_BOLD, 18, Color.BLACK,true, pdcs, "");
        writeLine(PDType1Font.HELVETICA_BOLD, 9, Color.BLACK,true, pdcs, "Screening-Station:    ");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,false, pdcs, screeningStation);
        writeLine(PDType1Font.HELVETICA_BOLD, 9, Color.BLACK,true, pdcs, "Kürzel/Nr. der Testlinie:     ");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,false, pdcs, "________________________");
        writeLine(PDType1Font.HELVETICA_BOLD, 9, Color.BLACK,true, pdcs, "Datum / Uhrzeit:     ");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,false, pdcs, "__________________ / ____________:____________                                _____________________________");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,true, pdcs, "                                                                                                                                                              Fortlaufende Proben-Nummer");
        writeLine(PDType1Font.HELVETICA_BOLD, 12, Color.BLACK,true, pdcs, "Testergebnis ");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,false, pdcs, "                                                                                                                                      oder Barcode-Etikett");
        writeLine(PDType1Font.HELVETICA_BOLD, 18, Color.BLACK,true, pdcs, "");
        writeLine(PDType1Font.HELVETICA_BOLD, 18, Color.BLACK,true, pdcs, "");
        writeLine(PDType1Font.HELVETICA_BOLD, 18, Color.BLACK,true, pdcs, "");
        writeLine(PDType1Font.HELVETICA, 12, Color.BLACK,true, pdcs, "                  " + TestResult.NEGATIV + "                                          " + TestResult.POSITIV + "                                             " + TestResult.INVALID);
        writeLine(PDType1Font.HELVETICA_BOLD, 18, Color.BLACK,true, pdcs, "");
        writeLine(PDType1Font.HELVETICA_BOLD, 18, Color.BLACK,true, pdcs, "");
        writeLine(PDType1Font.HELVETICA_BOLD, 18, Color.BLACK,true, pdcs, "");
        writeLine(PDType1Font.HELVETICA_BOLD, 9, Color.BLACK,true, pdcs, "Ablesung des Ergebnisses durch (Vorname Nachname)");
        writeLine(PDType1Font.HELVETICA, 9, Color.BLACK,false, pdcs, "                                                _______________________________");
        writeLine(PDType1Font.HELVETICA, 8, Color.BLACK,true, pdcs, "                                                                                                                                                                           Handzeichen oder Unterschrift");

        pdcs.endText();
        pdcs.close();
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        registration.save(bao);
        registration.close();
        return bao.toByteArray();
    }

    private void addTextField(int x, int y, int width, int height, PDDocument registration) throws IOException {
        PDPage page = registration.getPage(0);
        PDAcroForm acro = registration.getDocumentCatalog().getAcroForm();
        PDTextField textField = new PDTextField(acro);
        textField.setPartialName("SampleField" + x + y);
        acro.getFields().add(textField);
        PDAnnotationWidget widget = textField.getWidgets().get(0);
        PDRectangle rect = new PDRectangle(x, y, width, height);
        widget.setRectangle(rect);
        widget.setPage(page);

        PDAppearanceCharacteristicsDictionary fieldAppearance = new PDAppearanceCharacteristicsDictionary(new COSDictionary());
        fieldAppearance.setBorderColour(new PDColor(new float[]{0,0,0}, PDDeviceRGB.INSTANCE));
        widget.setAppearanceCharacteristics(fieldAppearance);
        widget.setPrinted(true);
        page.getAnnotations().add(widget);
    }

    private void addCheckbox(int x, int y, int width, int height, PDDocument registration)
    {
        PDPage page = registration.getPage(0);
        PDAcroForm acro = registration.getDocumentCatalog().getAcroForm();
        COSDictionary normalAppearances = new COSDictionary();
        PDAppearanceDictionary pdAppearanceDictionary = new PDAppearanceDictionary();
        pdAppearanceDictionary.setNormalAppearance(new PDAppearanceEntry(normalAppearances));
        pdAppearanceDictionary.setDownAppearance(new PDAppearanceEntry(normalAppearances));

        PDAppearanceStream pdAppearanceStream = new PDAppearanceStream(registration);
        pdAppearanceStream.setResources(new PDResources());
        try (PDPageContentStream pdPageContentStream = new PDPageContentStream(registration, pdAppearanceStream))
        {
            pdPageContentStream.setFont(PDType1Font.ZAPF_DINGBATS, (int)((width + height) / 2.5));
            pdPageContentStream.beginText();
            pdPageContentStream.newLineAtOffset(3, 4);
            pdPageContentStream.showText("\u2714");
            pdPageContentStream.endText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdAppearanceStream.setBBox(new PDRectangle(width, height));
        normalAppearances.setItem("Yes", pdAppearanceStream);


        PDCheckBox checkBox = new PDCheckBox(acro);
        acro.getFields().add(checkBox);
        checkBox.setPartialName("CheckBoxField" + x + y);
        checkBox.setFieldFlags(4);

        List<PDAnnotationWidget> widgets = checkBox.getWidgets();
        for (PDAnnotationWidget pdAnnotationWidget : widgets)
        {
            pdAnnotationWidget.setRectangle(new PDRectangle(x, y, width, height));
            pdAnnotationWidget.setPage(page);
            try {
                page.getAnnotations().add(pdAnnotationWidget);
            } catch (IOException e) {
                e.printStackTrace();
            }

            pdAnnotationWidget.setAppearance(pdAppearanceDictionary);
        }

// checkBox.setReadOnly(true);
        try {
            checkBox.unCheck();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeLine(PDType1Font font, int size, Color textColor, boolean newLine, PDPageContentStream pdcs, String text)
    {
        try {
            if(newLine) {
                pdcs.newLine();
            }
            pdcs.setFont(font, size);
            pdcs.setNonStrokingColor(textColor);
            pdcs.showText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
