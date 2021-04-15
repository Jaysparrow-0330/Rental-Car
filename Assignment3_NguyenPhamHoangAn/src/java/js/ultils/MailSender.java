/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package js.ultils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author jack3
 */
public class MailSender {

    private final static Logger LOG = Logger.getLogger(MailSender.class);

    public boolean send(String email, String code) {
        String to = email;
        if (to == null || "".equals(to)) {
            return false;
        }
        String from = "jack3460@gmail.com";
        final String username = "jack3460@gmail.com";
        final String password = "0996538580";
        Properties p = new Properties();
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", 587);
        Session s = Session.getInstance(p,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject("NURA RENTAL CAR");
            String content = "Your valid code is: " + code;
            msg.setText(content);
            Transport.send(msg);
        } catch (MessagingException e) {
            LOG.error(e);
            return false;
        }
        return true;
    }
}
