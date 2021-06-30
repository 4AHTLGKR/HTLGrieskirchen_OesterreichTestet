package at.htlgkr.htltestet.pdf;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

@Data
public class ResultPDF extends PDFData {
    private String firstname;
    private String lastname;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthdate;
    private String location;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime testingDate;
    private TestResult testResult;
    @JsonIgnore
    private List<Line> lines;


    @Override
    public byte[] createPDF() throws IOException {
        fillContent();
        PDDocument result = new PDDocument();
        result.addPage(new PDPage());
        PDPageContentStream pdcs = new PDPageContentStream(result, result.getPage(0));

        BufferedImage bi = ImageIO.read(new File("src\\main\\resources\\Images\\Logo_HTLBA_Grieskirchen_Big.png"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "png", baos);
        byte[] bytes = baos.toByteArray();
        PDImageXObject pdImage = PDImageXObject.createFromByteArray(result, bytes, "");
        pdcs.drawImage(pdImage, 365, 660);

        pdcs.beginText();
        pdcs.setLeading(14.5f);
        pdcs.newLineAtOffset(50, 650);

        for(Line l : lines)
        {
            pdcs.newLine();
            if(l.isHas2LinesNothing()) {
                pdcs.newLine();
                pdcs.newLine();
            }
            pdcs.setFont(l.getFont(), l.getFontSize());
            pdcs.showText(l.getText());
        }
        pdcs.endText();
        pdcs.close();
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        result.save(bao);
        result.close();
        return bao.toByteArray();
    }


    private void fillContent(){
        lines = new ArrayList<>();
        Line l = new Line();
        l.setFont(PDType1Font.HELVETICA_BOLD);
        l.setFontSize(12);
        l.setText("Aktion \"HTL testet\"");
        l.setHas2LinesNothing(false);
        lines.add(l);


        l = new Line();
        l.setFontSize(16);
        l.setFont(PDType1Font.HELVETICA_BOLD);
        l.setHas2LinesNothing(true);
        l.setText("                                  ERGEBNISPROTOKOLL");
        lines.add(l);


        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setHas2LinesNothing(true);
        l.setText("Dieses Schreiben protokolliert einen SARS-CoV-2-Test");
        lines.add(l);

        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setText("im Rahmen der Aktion \"HTL testet\".");
        lines.add(l);

        l = new Line();
        l.setFontSize(16);
        l.setFont(PDType1Font.HELVETICA_BOLD);
        l.setText("GETESTETE PERSON:");
        l.setHas2LinesNothing(true);
        lines.add(l);

        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setText("Herr/Frau:                    " + firstname);
        l.setHas2LinesNothing(false);
        lines.add(l);

        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setText("                                    " + lastname);
        l.setHas2LinesNothing(false);
        lines.add(l);

        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setText("Geburtsdatum:            " + birthdate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        l.setHas2LinesNothing(false);
        lines.add(l);

        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setText("Test-Typ:                     " + "Antigen-Test auf SARS-CoV-2");
        l.setHas2LinesNothing(false);
        lines.add(l);

        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setText("");
        l.setHas2LinesNothing(false);
        lines.add(l);

        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setText("Ort:                              " + location);
        l.setHas2LinesNothing(false);
        lines.add(l);

        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setText("Datum/Uhrzeit der      " + testingDate.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        l.setHas2LinesNothing(false);
        lines.add(l);

        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setText("Probenentnahme:                            ");
        l.setHas2LinesNothing(false);
        lines.add(l);

        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setText("");
        l.setHas2LinesNothing(true);
        lines.add(l);

        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setText("");
        l.setHas2LinesNothing(true);
        lines.add(l);

        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setText("Zum Zeitpunkt der Probenentahme lautet das Testergebnis:");
        l.setHas2LinesNothing(true);
        lines.add(l);

        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setText("");
        l.setHas2LinesNothing(false);
        lines.add(l);

        l = new Line();
        l.setFontSize(16);
        l.setFont(PDType1Font.HELVETICA_BOLD);
        l.setText("                                                  " + testResult);
        l.setHas2LinesNothing(false);
        lines.add(l);

        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setText("");
        l.setHas2LinesNothing(true);
        lines.add(l);

        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setText("Dieser Nachweis ist ausschließlich durch die darin angeführte Person für die in den");
        l.setHas2LinesNothing(true);
        lines.add(l);

        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setText("jeweiligen Verordnungen festgelegten Zwecke zu verwenden. Eine missbräuchliche");
        l.setHas2LinesNothing(false);
        lines.add(l);

        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setText("Verwendung oder Verfälschung des Nachweises kann strafrechtliche Konsequenzen");
        l.setHas2LinesNothing(false);
        lines.add(l);

        l = new Line();
        l.setFontSize(12);
        l.setFont(PDType1Font.HELVETICA);
        l.setText("zur Folge haben.");
        l.setHas2LinesNothing(false);
        lines.add(l);


    }
}
