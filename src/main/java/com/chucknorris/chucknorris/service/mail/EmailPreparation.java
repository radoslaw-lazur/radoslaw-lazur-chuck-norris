package com.chucknorris.chucknorris.service.mail;

import com.chucknorris.chucknorris.domain.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailPreparation {
    private MailCreatorService mailCreatorService;

    @Autowired
    public EmailPreparation(MailCreatorService mailCreatorService) {
        this.mailCreatorService = mailCreatorService;
    }

    public MimeMessagePreparator createMimeMessageJoke(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getMessage(), true);
        };
    }

    public String prepareJokeMail(final Mail mail) {
        return mailCreatorService.buildJokeMail(mail.getMessage());
    }
}
