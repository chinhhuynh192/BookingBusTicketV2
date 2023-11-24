package com.busticketbooking.busticketbooking.Utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailService {
    public static void sendEmail(String host, String port,
                                 final String userName, final String password, String toAddress,
                                 String subject, String body) throws AddressException,
            MessagingException {

        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };

        Session session = Session.getInstance(properties, auth);

        MimeMessage message = new MimeMessage(session);

        // Đặt thông tin người gửi và người nhận
        message.setFrom(new InternetAddress(userName));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));

        // Đặt tiêu đề và nội dung email
        message.setSubject(subject, "UTF-8");
        message.setContent(body, "text/html; charset=UTF-8");

        // Gửi email
        Transport.send(message);

    }
}
