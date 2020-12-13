package entities;



import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
    private final String receiverMail;
    public String newpassword = Integer.toString(rnd(1000000,9999999));

    public MailSender(String receiverMail){
        this.receiverMail = receiverMail;
        SenderMail();
    }
    private void  SenderMail(){
        Properties properties = System.getProperties();
        String host = "127.0.0.1";
        properties.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(properties);
        System.out.println("Start");
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(receiverMail));
            String to = "dimaformago281@gmail.com";
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Password recovery");

            message.setText("Your new password: "+ newpassword);

            Transport.send(message);
            System.out.println("Email Sent successfully....");
        } catch (MessagingException mex){ mex.printStackTrace(); }


    }
    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
