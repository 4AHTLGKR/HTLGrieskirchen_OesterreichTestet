package at.htlgkr.htltestet.Mail;

import at.htlgkr.htltestet.data.RegistrationData;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.Date;
import java.util.Properties;

public class SendEmails {

    private static final String URL = "http://localhost:8080";
    private static void sendMail(String mailContent, String to) throws IOException {



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

            //msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("Julian.m.schwaiger@gmail.com"));
            msg.setSubject("Österreich testet");

            msg.setContent(mailContent, "text/html");
            msg.setSentDate(new Date());
            Transport.send(msg);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void sendRegistrationMail(RegistrationData regis){
        RestTemplate restTemplate = new RestTemplate();

        String mailContent = restTemplate.getForObject(URL +"/Mail/Registration", String.class);
        try{
            sendMail(mailContent.replace("@authLink",URL+"/authentication?registrationId="+regis.getId()),regis.getEmail());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
