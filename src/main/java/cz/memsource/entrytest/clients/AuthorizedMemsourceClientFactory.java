package cz.memsource.entrytest.clients;

import cz.memsource.entrytest.token.ApiTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class AuthorizedMemsourceClientFactory {

    private final ApiTokenProvider apiTokenProvider;

    @Value("${memsource.api.baseUrl}")
    private String memsourceApiBaseUrl;

    public WebClient client() {
        return WebClient.builder()
                .baseUrl(memsourceApiBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Authorization", "ApiToken " + apiTokenProvider.getApiToken())
                .build();
    }
}
