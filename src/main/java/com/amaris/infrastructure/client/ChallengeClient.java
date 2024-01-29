package com.amaris.infrastructure.client;


import com.amaris.business.dto.EmployeeResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;


@Component
public class ChallengeClient {
private static final String EMPLOYEE_BY_ID_ENDPOINT = "/employee";

    private static final String EMPLOYEE_ENDPOINT = "/employees";
    private static final int MAX_RETRIES = 3;
    private static final Duration RETRY_BACKOFF = Duration.ofSeconds(3);


    @Value("${endpoint.challenge.url}")
    private String challengeUrl;

    private WebClient getWebclient() {
        return WebClient.builder().baseUrl(this.challengeUrl).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }

    public EmployeeResponseDto getAll() {
        return this.getWebclient()
                .get()
                .uri(EMPLOYEE_ENDPOINT)
                .retrieve()
                .bodyToMono(EmployeeResponseDto.class)
                .retryWhen(Retry.backoff(MAX_RETRIES, RETRY_BACKOFF))
                .block();
    }

    public EmployeeResponseDto getById(final long id) {
        return this.getWebclient()
                .get()
                .uri(EMPLOYEE_BY_ID_ENDPOINT + "/" + id)
                .retrieve()
                .bodyToMono(EmployeeResponseDto.class)
                .retryWhen(Retry.backoff(MAX_RETRIES, RETRY_BACKOFF))
                .block();
    }

}
