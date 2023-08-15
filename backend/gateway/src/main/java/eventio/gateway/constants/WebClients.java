package eventio.gateway.constants;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

public class WebClients {
    public static WebClient authClient =  WebClient.builder().baseUrl(Routes.authPath).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).defaultUriVariables(Collections.singletonMap("url", Routes.authPath)).build();
    public static WebClient concertClient = WebClient.builder().baseUrl(Routes.concertsPath).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).defaultUriVariables(Collections.singletonMap("url", Routes.concertsPath)).build();
    public static WebClient venueClient = WebClient.builder().baseUrl(Routes.venuesPath).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).defaultUriVariables(Collections.singletonMap("url", Routes.venuesPath)).build();
    public static WebClient userClient = WebClient.builder().baseUrl(Routes.usersPath).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).defaultUriVariables(Collections.singletonMap("url", Routes.usersPath)).build();
    public static WebClient ticketClient = WebClient.builder().baseUrl(Routes.ticketsPath).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).defaultUriVariables(Collections.singletonMap("url", Routes.ticketsPath)).build();


}
