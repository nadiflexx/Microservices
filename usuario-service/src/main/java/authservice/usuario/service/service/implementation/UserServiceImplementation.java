package authservice.usuario.service.service.implementation;

import authservice.usuario.service.entity.User;
import authservice.usuario.service.exceptions.DataNotFoundException;
import authservice.usuario.service.models.Car;
import authservice.usuario.service.repository.UserRepository;
import authservice.usuario.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private UserRepository repository;

//    public List<Car> getAllCars(String userId) {
//        return restTemplate.getForObject("http://localhost:8082/user" + userId, List.class);
//    }

    @Override
    public User saveUser(User user) {
        user.setId(UUID.randomUUID().toString());
        return repository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = repository.findAll();
        if (users.isEmpty()) throw new DataNotFoundException("No users found");
        return repository.findAll();
    }

    @Override
    public Optional<User> getUserById(String id) {
        return Optional.of(repository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found " + id)));
    }

    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        repository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found " + id));
        repository.deleteById(id);
    }
}
