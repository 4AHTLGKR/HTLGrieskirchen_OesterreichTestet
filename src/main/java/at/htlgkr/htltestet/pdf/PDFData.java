package at.htlgkr.htltestet.pdf;

import lombok.Data;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;
import java.time.LocalDateTime;

@Data
public abstract class PDFData {
    private int id;
    private LocalDateTime creationDate;

    public abstract PDDocument createPDF() throws IOException;
}
