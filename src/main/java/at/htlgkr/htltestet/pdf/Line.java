package at.htlgkr.htltestet.pdf;

import lombok.Data;
import org.apache.pdfbox.pdmodel.font.PDFont;

@Data
public class Line {
    private String text;
    private PDFont font;
    private float fontSize;
    private boolean has2LinesNothing;
}
