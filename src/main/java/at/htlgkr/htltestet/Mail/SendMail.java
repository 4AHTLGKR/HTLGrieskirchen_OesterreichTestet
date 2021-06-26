import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendMail {

    public static void main(String[] args){
        try{
            sendMail("Hallo Julian wie geht es dir?");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void sendMail(String msg) throws MessagingException {
        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "25");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("Wieso.Existiere.Ich@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse("julian.m.schwaiger@gmail.com"));
        message.setSubject("Java_Test_Mail");


        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
        System.out.println("Sent Mail");
    }
}
