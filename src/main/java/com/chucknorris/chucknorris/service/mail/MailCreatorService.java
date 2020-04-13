package com.chucknorris.chucknorris.service.mail;

import com.chucknorris.chucknorris.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    private AdminConfig adminConfig;
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    private Context context;

    @Autowired
    public MailCreatorService(AdminConfig adminConfig, TemplateEngine templateEngine) {
        this.adminConfig = adminConfig;
        this.templateEngine = templateEngine;
    }

    public String buildJokeMail(final String message) {
        context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mail/joke_mail", context);
    }
}
