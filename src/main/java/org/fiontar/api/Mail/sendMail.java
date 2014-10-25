/*
 * A class to implement the process of sending an email
 */
package org.fiontar.api.Mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.fiontar.api.Database.Constants;

public class sendMail {

    /**
     * method sets up the message contents
     *
     * @return message format
     */
    private static int i=0;
    private static Message setUp() {


        Properties props = new Properties();
        props.put("mail.smtp.socketFactory.port", Constants.MAILPORT);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", Constants.MAILSERVER);
        props.put("mail.smtp.port", Constants.MAILPORT);

        Session session;

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Constants.EMAIL_USERNAME, Constants.EMAIL_PASSWORD);
            }
        });
        try {
            Message message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(Constants.EMAIL_USERNAME, "Are You Ready? 2013"));
            } catch (UnsupportedEncodingException ex) {
                message.setFrom(new InternetAddress(Constants.EMAIL_USERNAME));
            }
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(
                    "isuru.11@cse.mrt.ac.lk"));
            return message;

        } catch (MessagingException e) {
            System.out.println("Email setup failed");
            System.out.println("Error " + e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * methods used for emailing text
     *
     * @param email email address
     * @param title message title
     * @param text message content
     */
    public static void sendmail(String email, String title, String text) {
        Message message = setUp();
        try {
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject(title);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException ex) {
            System.out.println("Email sending failed to " + email);
            System.out.println("Error " + ex.getLocalizedMessage());
        }
    }

    /**
     * method implements email sending process of contactus form
     *
     * @param emailTo receiver email address
     * @param emailReplyTo reply to email address
     * @param title message title
     * @param text message content
     */
    public static void sendmail(String emailTo, String emailReplyTo, String title, String text) {
        Message message = setUp();
        try {
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailTo));
            message.setSubject(title);
            message.setText(text);
            message.setReplyTo(InternetAddress.parse(emailReplyTo));
            Transport.send(message);
        } catch (MessagingException ex) {
            System.out.println("Email sending failed to us from " + emailReplyTo + " " + text);
        }
    }

    public static void sendmailBCC(String emails, String title, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.socketFactory.port", Constants.MAILPORT);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", Constants.MAILSERVER);
        props.put("mail.smtp.port", Constants.MAILPORT);

        Session session;
        i=i%5;
        i++;
        Authen a = new Authen("noreply"+i+"@rotaractmora.org");
        session = Session.getInstance(props,a);
        try {
            Message message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(Constants.EMAIL_USERNAME, "Are You Ready? 2013"));
            } catch (UnsupportedEncodingException ex) {
                message.setFrom(new InternetAddress(Constants.EMAIL_USERNAME));
            }
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(
                    "isuru.11@cse.mrt.ac.lk"));
            message.addRecipients(Message.RecipientType.BCC,
                    InternetAddress.parse(emails));
            message.setSubject(title);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException ex) {
            System.out.println("Email sending failed to " + emails);
            System.out.println("Error " + ex.getLocalizedMessage());
        }
    }
}

class Authen extends javax.mail.Authenticator {

    String email;

    public Authen(String email) {
        this.email = email;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(email, Constants.EMAIL_PASSWORD);
    }
}