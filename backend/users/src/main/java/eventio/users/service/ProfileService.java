package eventio.users.service;

import eventio.users.model.Profile;

import java.util.UUID;

public interface ProfileService {
    void register(UUID id, String name, String address, String phone);
    Profile findById(UUID id);
}
