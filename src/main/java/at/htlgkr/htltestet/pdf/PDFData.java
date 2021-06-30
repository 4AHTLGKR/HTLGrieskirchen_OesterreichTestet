package at.htlgkr.htltestet.pdf;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public abstract class PDFData {
    protected int id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    protected LocalDateTime creationDate;
    public abstract byte[] createPDF() throws Exception;
}
