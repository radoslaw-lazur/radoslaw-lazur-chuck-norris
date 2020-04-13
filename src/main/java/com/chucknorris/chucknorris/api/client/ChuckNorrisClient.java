package com.chucknorris.chucknorris.api.client;

import com.chucknorris.chucknorris.api.config.ApiConfig;
import com.chucknorris.chucknorris.domain.JokeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.util.Optional.ofNullable;

@Slf4j
@Component
public class ChuckNorrisClient {
    private RestTemplate restTemplate;
    private ApiConfig apiConfig;

    @Autowired
    public ChuckNorrisClient(RestTemplate restTemplate, ApiConfig apiConfig) {
        this.restTemplate = restTemplate;
        this.apiConfig = apiConfig;
    }

    public JokeDto getChuckNorrisJoke() {
        try {
            return ofNullable(restTemplate.getForObject(getURL(), JokeDto.class)).orElseThrow(RuntimeException::new);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new JokeDto();
        }
    }

    private URI getURL() {
        return UriComponentsBuilder.fromHttpUrl(apiConfig.getApiEndpoint()).build().encode().toUri();
    }
}

