package eventio.gateway.controller.auth;

import eventio.gateway.dto.LoginDto;
import eventio.gateway.dto.RegisterDto;
import eventio.gateway.dto.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static eventio.gateway.constants.WebClients.authClient;

@RequestMapping("/auth")
@RestController
public class AuthController {

    @PostMapping("/register")
    @Operation(summary = "Register a new user.")
    public void register(@RequestBody RegisterDto data) {
        var response = authClient.post().uri("/auth/register").body(BodyInserters.fromValue(data)).retrieve().bodyToMono(String.class).block();
    }

    @PostMapping("/login")
    @Operation(summary = "Log in.")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto data) {
        Mono<TokenDto> response = authClient.post().uri("/auth/login").body(BodyInserters.fromValue(data)).retrieve().bodyToMono(new ParameterizedTypeReference<>() {
        });
        TokenDto token = response.block();
        return ResponseEntity.ok(token);
    }
}
