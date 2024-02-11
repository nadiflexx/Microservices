package authservice.usuario.service.service;

import authservice.usuario.service.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(String id);

    User updateUser(User user);

    void deleteUser(String id);

    Optional<User> getAllCars(String id);

    Optional<User> getAllMotos(String id);

}
