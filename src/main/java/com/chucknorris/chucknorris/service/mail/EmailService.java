package com.chucknorris.chucknorris.service.mail;

import com.chucknorris.chucknorris.domain.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {
    @Qualifier("sender1")
    private JavaMailSender javaMailSender;
    private EmailPreparation preparation;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, EmailPreparation preparation) {
        this.javaMailSender = javaMailSender;
        this.preparation = preparation;
    }

    public void sendJokeMail(final Mail mail) {
        log.info("Starting e-mail preparation... to " + mail.getMailTo() + " : " + mail.getSubject());
        try {
            String jokeMail = preparation.prepareJokeMail(mail);
            mail.setMessage(jokeMail);
            javaMailSender.send(preparation.createMimeMessageJoke(mail));
            log.info("Email to " + mail.getMailTo() + " has been sent" + " regarding " + mail.getSubject());
        } catch (MailException e) {
            log.info("Failed to process e-mail sending to " + mail.getMailTo() + " regarding "
                    + mail.getSubject() + " {}", e.getMessage(), e);
        }
    }
}
