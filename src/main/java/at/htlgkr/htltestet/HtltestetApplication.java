package at.htlgkr.htltestet;

import at.htlgkr.htltestet.pdf.Registration;
import at.htlgkr.htltestet.pdf.Result;
import at.htlgkr.htltestet.pdf.TestResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class HtltestetApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(HtltestetApplication.class, args);

        Result r = new Result();
        r.setId(2);
        r.setCreationDate(LocalDateTime.now());
        r.setFirstname("Lars");
        r.setLastname("Novid");
        r.setBirthdate(LocalDate.now());
        r.setTestingDate(LocalDateTime.now());
        r.setLocation("Leonding");
        r.setTestResult(TestResult.POSITIV);
        try {
            r.createPDF();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Registration reg = new Registration();
        reg.setId(3);
        reg.setCreationDate(LocalDateTime.now());
        reg.setCode("169420");
        reg.setScreeningStation("Schulzentrum Raiffeisen Arena, Grieskirchen");
        BufferedImage bi = null;
        try {
            reg.createPDF();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
