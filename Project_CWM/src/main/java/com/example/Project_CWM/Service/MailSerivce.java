package com.example.Project_CWM.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSerivce {

    @Autowired
    private JavaMailSender javaMailSender;
    private String number;
    private String setFrom = "mailsend7890@gmail.com";

    public void createNumber() {
        int num = (int)(Math.random() * (90000)) + 10000;

        number = String.valueOf(num);
    }

    public MimeMessage createMail(String userEmail) throws MessagingException {

        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        String ToEmail = userEmail;
        String title = "CWM 회원가입 인증번호입니다.";
        String text =  "";
        text += "<div style=\"margin: 50px;\">";
        text += "<h2 style=\"margin-left: 83px;\">CWM 회원가입 이메일 인증 입니다.</h2>";
        text += "<br>";
        text += "<div style=\"border: 1px solid #c5baba; text-align: center; width: 500px;\">";
        text += "<p>인증 번호 : <strong>";
        text += number + "</strong></p>";
        text += "<br>";
        text += "<p>인증 번호를 입력해주세요. 감사합니다</p>";
        text += "</div>";

        message.setFrom(setFrom);
        message.addRecipients(MimeMessage.RecipientType.TO, ToEmail);
        message.setSubject(title);
        message.setText(text,"utf-8","html");

        return message;
    }

    public  String sendEmail(String userEmail) throws MessagingException {

        MimeMessage EmailSend = createMail(userEmail);

        javaMailSender.send(EmailSend);

        return number;
    }
}
