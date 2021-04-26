package at.htlgkr.htltestet;

import at.htlgkr.htltestet.pdf.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class HtltestetApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(HtltestetApplication.class, args);

    }

}
