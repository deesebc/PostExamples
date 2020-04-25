package com.home.sample.mailsender.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.IOUtils;

public class MailSenderUtil {
    public static void main(final String[] args) {
        sendEmail("${mail.user}", "${mail.pass}", "${mail.from}", "${mail.to}", "Subject");
    }

    public static void sendEmail(final String username, final String password, final String fromEmail,
            final String toEmail, final String messageSubject) {
        Session session = configureServer(username, password);
        try {
            Message message = configureMessage(fromEmail, toEmail, messageSubject, session);
            MimeMultipart content = new MimeMultipart("related");
            createTemplate(content, "content/html/basic.html", "UTF-8", "html");
            addImage(content, "content/images/header-wallpaper.jpg", "header-wallpaper", Part.INLINE);
            message.setContent(content);
            // Send email message
            Transport.send(message);
        } catch (MessagingException except) {
            throw new RuntimeException(except);
        }
    }

    private static Message configureMessage(final String fromEmail, final String toEmail,
            final String messageSubject, final Session session) throws MessagingException, AddressException {
        // Create message with session
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(messageSubject);
        return message;
    }

    private static Session configureServer(final String username, final String password) {
        // Set properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        // Create session & authenticate with mail server
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        return session;
    }

    private static void addImage(final MimeMultipart content, final String relFilePath,
            final String contentId, final String disposition) {
        try {
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            DataSource fds = new InputStreamDataSource(
                    MailSenderUtil.class.getClassLoader().getResource(relFilePath).openStream(), contentId,
                    "image/jpg");
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<" + contentId + ">");
            messageBodyPart.setDisposition(disposition);
            messageBodyPart.setFileName(contentId);
            content.addBodyPart(messageBodyPart);
        } catch (MessagingException | IOException except) {
            except.printStackTrace();
        }
    }

    private static void createTemplate(final MimeMultipart content, final String relFilePath,
            final String encoding, final String type) {
        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(MailSenderUtil.class.getClassLoader().getResource(relFilePath).openStream(), writer,
                    encoding);
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(writer.toString(), encoding, type);
            content.addBodyPart(textPart);
        } catch (MessagingException | IOException except) {
            except.printStackTrace();
        }
    }
}
