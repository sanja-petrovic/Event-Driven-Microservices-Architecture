package eventio.auth.service.authority;

import eventio.auth.model.Authority;
import eventio.auth.model.AuthorityType;
import eventio.auth.repository.AuthorityRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository repository;

    public AuthorityServiceImpl(AuthorityRepository repository) {
        this.repository = repository;
    }

    @Override
    @PostConstruct
    public void initialize() {
        if (repository.findAll().size() == 0) {
            repository.saveAll(Arrays.asList(
                    new Authority(AuthorityType.ADMIN),
                    new Authority(AuthorityType.CLIENT)
            ));
        }
    }
}
