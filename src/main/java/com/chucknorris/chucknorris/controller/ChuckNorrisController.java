package com.chucknorris.chucknorris.controller;

import com.chucknorris.chucknorris.domain.EmailDto;
import com.chucknorris.chucknorris.service.JokeService.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class ChuckNorrisController {
    private JokeService jokeService;

    @Autowired
    public ChuckNorrisController(JokeService jokeService) {
        this.jokeService = jokeService;

    }

    @PostMapping(value = "/jokes")
    public void sendJoke(@RequestBody EmailDto emailDto) {
        jokeService.createMail(emailDto.getEmail());
    }

    @GetMapping(value = "/jokes")
    public void sendJokeParam(@RequestParam String yourEmail) {
        jokeService.createMail(yourEmail);
    }
}
