package eventio.auth.service.account;

import eventio.auth.exception.NotFoundException;
import eventio.auth.exception.UnrecognizedAuthority;
import eventio.auth.model.Account;
import eventio.auth.model.AuthorityType;
import eventio.auth.repository.AccountRepository;
import eventio.auth.repository.AuthorityRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;

    public AccountServiceImpl(AccountRepository accountRepository, AuthorityRepository authorityRepository) {
        this.accountRepository = accountRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void register(UUID id, String email, String password, AuthorityType authorityType) {
        Account account = new Account(
                id,
                email,
                password,
                authorityRepository.findByType(authorityType).orElseThrow(() -> new UnrecognizedAuthority(authorityType.toString()))

        );
        if (AuthorityType.ADMIN == authorityType) {
            account.setActive(true);
        }
        accountRepository.save(account);
    }

    @Override
    public void login(String email, String password) {
        /*Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Account account = (Account) authentication.getPrincipal();*/
    }

    @Override
    public Account getCurrent() {
        return (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();
    }

    @Override
    public void verify() {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByEmail(username).orElseThrow(() -> new NotFoundException(username));
        //return accountRepository.findById(UUID.fromString(username)).orElseThrow(() -> new NotFoundException(username));
    }
}
