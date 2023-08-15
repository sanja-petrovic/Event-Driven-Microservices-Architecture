package eventio.gateway.controller.auth;

import eventio.gateway.constants.Routes;
import eventio.gateway.dto.RegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

import static eventio.gateway.constants.WebClients.userClient;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final WebClient authClient = WebClient.builder().baseUrl(Routes.authPath).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).defaultUriVariables(Collections.singletonMap("url", Routes.authPath)).build();

    @PostMapping
    @Operation(summary = "Register a new user.")
    public void register(@RequestBody RegisterDto data) {
        authClient.post().uri("/auth/register").body(BodyInserters.fromValue(data)).retrieve();
    }
}
