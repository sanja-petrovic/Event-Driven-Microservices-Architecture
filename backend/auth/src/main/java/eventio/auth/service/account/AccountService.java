package eventio.auth.service.account;

import eventio.auth.model.Account;
import eventio.auth.model.AuthorityType;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface AccountService extends UserDetailsService {

    void register(UUID id, String email, String password, AuthorityType authorityType);

    void login(String email, String password);

    Account getCurrent();

    void logout();

    void verify();
}
