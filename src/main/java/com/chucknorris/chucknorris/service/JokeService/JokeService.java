package com.chucknorris.chucknorris.service.JokeService;

import com.chucknorris.chucknorris.api.client.ChuckNorrisClient;
import com.chucknorris.chucknorris.domain.JokeDto;
import com.chucknorris.chucknorris.domain.Mail;
import com.chucknorris.chucknorris.service.mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JokeService {
    private EmailService emailService;
    private ChuckNorrisClient chuckNorrisClient;

    @Autowired
    public JokeService(EmailService emailService, ChuckNorrisClient chuckNorrisClient) {
        this.emailService = emailService;
        this.chuckNorrisClient = chuckNorrisClient;
    }

    public void createMail(final String email) {
        JokeDto newJoke = chuckNorrisClient.getChuckNorrisJoke();
        emailService.sendJokeMail(new Mail(email, "New joke from Chuck Norris provided!! :)",
                newJoke.getValueDto().getJoke()));
    }
}
