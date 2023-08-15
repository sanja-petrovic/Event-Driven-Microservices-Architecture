package eventio.auth.service.account;

import eventio.auth.dto.OutboxRegisterDto;
import eventio.auth.dto.RegisterDto;
import eventio.auth.exception.NotFoundException;
import eventio.auth.exception.UnrecognizedAuthority;
import eventio.auth.model.Account;
import eventio.auth.model.AuthorityType;
import eventio.auth.model.OutboxMessage;
import eventio.auth.repository.AccountRepository;
import eventio.auth.repository.AuthorityRepository;
import eventio.auth.service.outbox.OutboxService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;
    private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final OutboxService outboxService;

    public AccountServiceImpl(AccountRepository accountRepository, AuthorityRepository authorityRepository, OutboxService outboxService) {
        this.accountRepository = accountRepository;
        this.authorityRepository = authorityRepository;
        this.outboxService = outboxService;
    }

    @Override
    @Transactional
    public void register(UUID id, String email, String password, AuthorityType authorityType) {
        Account account = new Account(
                email,
                password,
                authorityRepository.findByType(authorityType).orElseThrow(() -> new UnrecognizedAuthority(authorityType.toString()))

        );
        if (AuthorityType.ADMIN == authorityType) {
            account.setActive(true);
        }
        Account savedAccount = accountRepository.save(account);
    }

    @Override
    @Transactional
    public void register(RegisterDto data, String password, AuthorityType authorityType) {
        Account account = new Account(
                data.email(),
                password,
                authorityRepository.findByType(authorityType).orElseThrow(() -> new UnrecognizedAuthority(authorityType.toString()))

        );
        if (AuthorityType.ADMIN == authorityType) {
            account.setActive(true);
        }
        Account savedAccount = accountRepository.save(account);
        OutboxRegisterDto outboxPayload = new OutboxRegisterDto(savedAccount.getId(), data.name(), data.address(), data.phone());
        OutboxMessage outboxMessage = outboxService.generate("register", outboxPayload);
        outboxService.send(outboxMessage);
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
