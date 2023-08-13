package eventio.auth.repository;

import eventio.auth.model.Authority;
import eventio.auth.model.AuthorityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorityRepository extends JpaRepository<Authority, UUID> {
    Optional<Authority> findByType(AuthorityType type);
}
