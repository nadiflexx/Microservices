package authservice.usuario.service.service.implementation;

import authservice.usuario.service.entity.User;
import authservice.usuario.service.exceptions.DataNotFoundException;
import authservice.usuario.service.models.Car;
import authservice.usuario.service.models.Motorcycle;
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

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository repository;

    public Optional<User> getAllCars(String userId) {
        List<Car> carsList = restTemplate.getForObject("http://car-service/car/user/" + userId, List.class);
        Optional<User> user = getUserById(userId);
        user.ifPresent(value -> value.setCarsList(carsList));
        return user;
    }

    public Optional<User> getAllMotos(String userId) {
        List<Motorcycle> motoList = restTemplate.getForObject("http://motorcycle-service/moto/user/" + userId, List.class);
        Optional<User> user = getUserById(userId);
        user.ifPresent(value -> value.setMotoList(motoList));
        return user;
    }

    @Override
    public User saveUser(User user) {
        user.setId(UUID.randomUUID().toString());
        return repository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = repository.findAll();
        if (users.isEmpty()) throw new DataNotFoundException("No users found");
        for (User user: users) {
            getAllCars(user.getId());
            getAllMotos(user.getId());
        }
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
