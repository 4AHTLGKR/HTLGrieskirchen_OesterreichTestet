package at.htlgkr.htltestet.Mail;

import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.Date;
import java.util.Properties;

public class SendEmails {

    /*private static final String FROM = "austria.govcovidtest@gmail.com";
    private static final String PASS = "Abcd.1234";*/

    public static void sendMail() throws IOException {

        String url = "http://localhost:8080/Mail/Registration";

        File authFile = ResourceUtils.getFile("classpath:email/auth.txt");
        BufferedReader br = new BufferedReader(new FileReader(authFile));

        final String FROM = br.readLine();
        final String PASS = br.readLine();


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new GmailAuthenticator(FROM,PASS));
        try{

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("austria.govcovidtest@gmail.com"));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("placeholder")); //Hier mail von benutzer hinzufügen
            msg.setSubject("Österreich testet");

            RestTemplate restTemplate = new RestTemplate();

            msg.setContent(restTemplate.getForObject(url, String.class), "text/html");
            msg.setSentDate(new Date());
            Transport.send(msg);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
