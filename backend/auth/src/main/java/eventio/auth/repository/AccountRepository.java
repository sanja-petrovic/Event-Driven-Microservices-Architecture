package eventio.auth.repository;

import java.util.Optional;
import java.util.UUID;

import eventio.auth.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByEmail(String email);
}
