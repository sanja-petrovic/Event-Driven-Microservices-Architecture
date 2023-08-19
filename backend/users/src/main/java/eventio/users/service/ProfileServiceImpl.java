package eventio.users.service;

import eventio.users.exception.NotFoundException;
import eventio.users.model.Profile;
import eventio.users.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository repository;

    public ProfileServiceImpl(ProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void register(UUID id, String name, String address, String phone) {
        this.repository.save(new Profile(id, name, address, phone));
    }

    @Override
    public void register(Profile profile) {
        this.repository.save(profile);
    }

    @Override
    public Profile findById(UUID id) {
        return this.repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
