package eventio.users.controller;

import eventio.users.dto.RegisterDto;
import eventio.users.model.Profile;
import eventio.users.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/profiles")
@RestController
public class ProfileController {
    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user profile.")
    public void register(@RequestBody RegisterDto data) {
        this.service.register(UUID.fromString(data.id()), data.name(), data.address(), data.phone());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find user profile by ID.")
    public ResponseEntity<Profile> findById(@PathVariable String id) {
        return ResponseEntity.ok(this.service.findById(UUID.fromString(id)));
    }
}
