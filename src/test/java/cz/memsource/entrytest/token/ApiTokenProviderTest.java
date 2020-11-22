package cz.memsource.entrytest.token;

import cz.memsource.entrytest.clients.MemsourceClientFactory;
import cz.memsource.entrytest.setup.Account;
import cz.memsource.entrytest.setup.AccountService;
import cz.memsource.entrytest.util.TimeUtil;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ApiTokenProviderTest {

    @Mock
    private AccountService accountService;
    @Mock
    private TimeUtil timeUtil;
    @Mock
    private MemsourceClientFactory memsourceClientFactory;

    private ApiTokenProvider apiTokenProvider;

    public MockWebServer mockBackEnd;
    public String mockServerBaseUrl;

    private Account account = new Account("john.doe", "secret");
    private String expectedToken = "API_TOKEN_SECRET";

    @BeforeEach
    void beforeEach() throws Exception {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
        mockServerBaseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());

        Mockito.when(accountService.getAccount()).thenReturn(account);
        Mockito.when(memsourceClientFactory.client()).thenReturn(WebClient.builder()
                .baseUrl(mockServerBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build());

        mockBackEnd.enqueue(new MockResponse()
                .setBody("{\"token\":\"API_TOKEN_SECRET\",\"expires\":\"2020-11-22T20:00:00+0000\"}")
                .addHeader("Content-Type", "application/json"));
        mockBackEnd.enqueue(new MockResponse()
                .setBody("{\"token\":\"API_TOKEN_SECRET\",\"expires\":\"2020-11-25T12:00:00+0000\"}")
                .addHeader("Content-Type", "application/json"));

        apiTokenProvider = new ApiTokenProviderImpl(accountService, timeUtil, memsourceClientFactory);
    }

    @AfterEach
    void afterEach() throws Exception {
        mockBackEnd.shutdown();
    }

    @Test
    void getApiToken_firstCall() throws InterruptedException {
        //when
        String token = apiTokenProvider.getApiToken();

        //then
        assertEquals(expectedToken, token);

        assertEquals(1, mockBackEnd.getRequestCount());
        RecordedRequest recordedRequest = mockBackEnd.takeRequest();
        assertEquals("POST", recordedRequest.getMethod());
        assertEquals("/api2/v1/auth/login", recordedRequest.getPath());
        assertEquals("{\"userName\":\"john.doe\",\"password\":\"secret\"}",
                recordedRequest.getBody().readUtf8());
    }

    @ParameterizedTest
    @MethodSource("provideDataForGetApiToken_multipleCalls")
    void getApiToken_multipleCalls(boolean isFirstTokenValid, int loginRequestsCount) {
        //before
        Mockito.when(timeUtil.isTimestampInFarEnoughFuture(OffsetDateTime.parse("2020-11-22T20:00:00+00"),
                Duration.ofMinutes(5))).thenReturn(isFirstTokenValid);

        //when
        String token1 = apiTokenProvider.getApiToken();
        String token2 = apiTokenProvider.getApiToken();

        //then
        assertEquals(expectedToken, token1);
        assertEquals(expectedToken, token2);

        assertEquals(loginRequestsCount, mockBackEnd.getRequestCount());
    }

    private static Stream<Arguments> provideDataForGetApiToken_multipleCalls() {
        return Stream.of(
                Arguments.of(true, 1),
                Arguments.of(false, 2)
        );
    }
}
