package com.example.Quiz.Ultility;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class JavaMailUtility {
    private final  String subject ="Khôi phục tài khoản QuizApp_Hutech";
    private final String hello = "Chào bạn ";
    private final String content ="Nhấn vào link để lấy lại mật khẩu: ";
    private final String end= "địt mẹ mày có cái mật khẩu mà cũng quên";


    public void sendmail(String to, String username) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // just config ahhi do ngok
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("quizapphutech@gmail.com", "Nadeshiko5246");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("quizapphutech@gmail.com", false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject(subject);
        msg.setText(hello+username+" ,\n"+content+"\n"+end);
        msg.setSentDate(new Date());

//        MimeBodyPart messageBodyPart = new MimeBodyPart();
//        messageBodyPart.setContent("Tutorials point email", "text/html");
//
//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();
//
//        attachPart.attachFile("/var/tmp/image19.png");
//        multipart.addBodyPart(attachPart);
//        msg.setContent(multipart);
        Transport.send(msg);
    }
}
