package eventio.auth.controller;

import eventio.auth.dto.LoginDto;
import eventio.auth.dto.RegisterDto;
import eventio.auth.dto.TokenDto;
import eventio.auth.model.Account;
import eventio.auth.model.AuthorityType;
import eventio.auth.security.TokenHandler;
import eventio.auth.service.account.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/auth")
@RestController
@Slf4j
public class AuthController {
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenHandler tokenHandler;
    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AccountService accountService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenHandler tokenHandler) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenHandler = tokenHandler;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new account.")
    public void registerClient(@RequestBody RegisterDto data) {
        accountService.register(data, passwordEncoder.encode(data.password()), AuthorityType.CLIENT);
    }

    @PostMapping("/admin")
    @Operation(summary = "Register a new admin account.")
    public void registerAdmin(@RequestBody RegisterDto data) {
        accountService.register(data, passwordEncoder.encode(data.password()), AuthorityType.ADMIN);
    }

    @PostMapping("/login")
    @Operation(summary = "Log in.")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto data, HttpServletResponse response) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        data.email(),
                        data.password()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Account user = (Account) authentication.getPrincipal();
        String jwt = this.tokenHandler.generateToken(user.getId(), user.getEmail(), user.getAuthority());
        ResponseCookie jwtCookie = tokenHandler.generateCookie("accessToken", jwt, "/api");
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new TokenDto(jwt));
    }

    @PostMapping("/logout")
    @Operation(summary = "Log out.")
    public ResponseEntity logout() {
        ResponseCookie jwtCookie = tokenHandler.getCleanJwtCookie();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .build();
    }

    @GetMapping("/current")
    @Operation(summary = "Get the currently logged in user.")
    public ResponseEntity<Account> getCurrent() {
        return ResponseEntity.ok(this.accountService.getCurrent());
    }
}
