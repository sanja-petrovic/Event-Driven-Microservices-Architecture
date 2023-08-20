package eventio.gateway.controller.auth;

import eventio.gateway.dto.LoginDto;
import eventio.gateway.dto.RegisterDto;
import eventio.gateway.dto.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.time.Duration;

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
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto data, HttpServletRequest request, HttpServletResponse response) {
        Mono<TokenDto> r = authClient.post().uri("/auth/login").body(BodyInserters.fromValue(data)).retrieve().bodyToMono(new ParameterizedTypeReference<>() {
        });
        TokenDto token = r.block();
        ResponseCookie cookie = ResponseCookie.from("jwt", token.jwt())
                .httpOnly(true)
                .secure(true)
                .maxAge(Duration.ofHours(12))
                .sameSite("none")
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(token);
    }

    @PostMapping("/logout")
    @Operation(summary = "Log out.")
    public void logout() {
        authClient.post().uri("/auth/logout").retrieve().bodyToMono(String.class).block();
    }
}
