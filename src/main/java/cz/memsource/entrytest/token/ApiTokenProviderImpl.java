package cz.memsource.entrytest.token;

import cz.memsource.entrytest.clients.MemsourceClientFactory;
import cz.memsource.entrytest.setup.Account;
import cz.memsource.entrytest.setup.AccountService;
import cz.memsource.entrytest.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApiTokenProviderImpl implements ApiTokenProvider {

    private final AccountService accountService;
    private final TimeUtil timeUtil;
    private final MemsourceClientFactory memsourceClientFactory;

    private final Map<String, LoginResponse> tokenCache = new HashMap<>();

    /**
     * @return Memsource API token for a stored account
     */
    @Override
    public String getApiToken() {
        Account account = accountService.getAccount();
        LoginResponse token = tokenCache.get(account.getUserName());

        //get the new API token if needed
        if (token == null || !timeUtil.isTimestampInFarEnoughFuture(token.getExpires(), Duration.ofMinutes(5))) {
            //attempt to log in using stored memsource account
            //add some resiliency and repeat in case of error with non 401 response
            token = memsourceClientFactory.client().post().uri("/api2/v1/auth/login")
                    .body(BodyInserters.fromValue(account))
                    .retrieve().toEntity(LoginResponse.class)
                    .timeout(Duration.ofSeconds(2))
                    .retryWhen(Retry.fixedDelay(1, Duration.ofSeconds(1)).filter(
                            throwable -> !(throwable instanceof WebClientResponseException &&
                                    ((WebClientResponseException) throwable).getStatusCode() == HttpStatus.UNAUTHORIZED)
                    ))
                    .block().getBody();

            tokenCache.put(account.getUserName(), token);
        }

        return token.getToken();
    }
}
