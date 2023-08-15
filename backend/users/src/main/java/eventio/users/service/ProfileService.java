package eventio.users.service;

import eventio.users.model.Profile;

import java.util.UUID;

public interface ProfileService {
    void register(UUID id, String name, String address, String phone);
    void register(Profile profile);
    Profile findById(UUID id);
}
