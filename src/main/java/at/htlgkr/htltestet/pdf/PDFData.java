package at.htlgkr.htltestet.pdf;

import lombok.Data;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;
import java.time.LocalDateTime;
import at.htlgkr.htltestet.barcode.*;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public abstract class PDFData {
    protected int id;
    protected LocalDateTime creationDate;
    public abstract PDDocument createPDF() throws Exception;
}
