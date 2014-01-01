package seopays.util;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

@Component
public class MailSender {


    @Resource(name="mailProperties")
    private Properties mailProperties;


    public boolean send (String to, String subject, String body) {


        final String username = mailProperties.getProperty("mail.smtp.email");
        final String password = mailProperties.getProperty("mail.smtp.password");

        Properties props = new Properties();
        props.put("mail.smtp.auth", mailProperties.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", mailProperties.getProperty("mail.smtp.starttls.enable"));
        props.put("mail.smtp.host", mailProperties.getProperty("mail.smtp.host"));
        props.put("mail.smtp.port", mailProperties.getProperty("mail.smtp.port"));

        props.put("mail.debug", mailProperties.getProperty("mail.debug"));
        props.put("mail.smtp.ssl.trust", mailProperties.getProperty("mail.smtp.ssl.trust"));

        javax.mail.Session session = javax.mail.Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailProperties.getProperty("mail.smtp.email")));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            return true;

        } catch (MessagingException e) {
//            throw new RuntimeException(e);

            return false;
        }

    }
}
