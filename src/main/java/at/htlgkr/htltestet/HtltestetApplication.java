package at.htlgkr.htltestet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class HtltestetApplication {

    private static String fromMail;
    private static String passMail;
    private static String jwtSecret;
    public static void main(String[] args) throws IOException {

        SpringApplication.run(HtltestetApplication.class, args);

        File authFile = ResourceUtils.getFile("classpath:email/auth.txt");
        BufferedReader br = new BufferedReader(new FileReader(authFile));

        fromMail = br.readLine();
        passMail = br.readLine();
        jwtSecret = br.readLine();
    }

    public static String getFromMail() {
        return fromMail;
    }

    public static String getPassMail() {
        return passMail;
    }

    public static String getJwtSecret() {
        return jwtSecret;
    }
}
