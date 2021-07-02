package at.htlgkr.htltestet.Mail;

import at.htlgkr.htltestet.data.RegistrationData;
import at.htlgkr.htltestet.pdf.RegistrationPDF;
import at.htlgkr.htltestet.pdf.ResultPDF;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendEmails {

    private static final String URL = "http://localhost:8080";
    private static final Logger _LOGGER = Logger.getLogger("SendEmails");
    private static void sendMail(String mailContent, String to, byte[] pdf) throws IOException {



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
            _LOGGER.log(Level.INFO,"Sending mail to "+to);

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("austria.govcovidtest@gmail.com"));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject("Österreich testet");


            msg.setSentDate(new Date());

            if(pdf!=null){
                MimeBodyPart att = new MimeBodyPart();

                ByteArrayDataSource bds = new ByteArrayDataSource(pdf, "application/pdf");
                att.setDataHandler(new DataHandler(bds));
                att.setFileName(bds.getName());

                MimeBodyPart messagePart = new MimeBodyPart();
                messagePart.setContent(mailContent, "text/html");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messagePart);
                multipart.addBodyPart(att);


                msg.setContent(multipart);
            }
            else{
                msg.setContent(mailContent, "text/html");
            }

            Transport.send(msg);
            System.out.println("!!!!!!!!!!!!!! Sent mail to "+to+" successfully");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void sendRegistrationMail(RegistrationData regis, RegistrationPDF pdf){
        RestTemplate restTemplate = new RestTemplate();

        String mailContent = restTemplate.getForObject(URL +"/Mail/Registration", String.class);
        try{
            sendMail(mailContent.replace("@authLink",URL+"/authentication?registrationId="+regis.getId()),regis.getEmail(),pdf.createPDF());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void sendResultMail(ResultPDF rPDF, String mailTo){
        RestTemplate restTemplate = new RestTemplate();

        String mailContent = restTemplate.getForObject(URL +"/Mail/Result", String.class);

        try{
            sendMail(mailContent,mailTo,rPDF.createPDF());
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void sendStornoMail(RegistrationData regis){
        RestTemplate restTemplate = new RestTemplate();

        String mailContent = restTemplate.getForObject(URL +"/Mail/Storno", String.class);
        try{

            sendMail(mailContent,regis.getEmail(),null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
