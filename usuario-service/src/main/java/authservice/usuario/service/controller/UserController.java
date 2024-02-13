package authservice.usuario.service.controller;

import authservice.usuario.service.entity.User;
import authservice.usuario.service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveUser(user));
    }

    @GetMapping
    @CircuitBreaker(name = "getAllUsersBreaker", fallbackMethod = "getAllUsersFallback")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return service.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        return service.getUserById(id)
                .map(userDesired -> {
                    userDesired.setNombre(user.getNombre());
                    userDesired.setApellido(user.getApellido());
                    userDesired.setEmail(user.getEmail());

                    User userUpdate = service.updateUser(userDesired);
                    return new ResponseEntity<>(userUpdate, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable String id) {
        service.deleteUser(id);
        return new ResponseEntity<>("User deleted succesfully", HttpStatus.OK);
    }

    @GetMapping("/cars/{id}")
    @CircuitBreaker(name = "getAllUsersCarsBreaker", fallbackMethod = "getAllUsersCarsFallback")
    public ResponseEntity<Optional<User>> getAllUsersCars(@PathVariable String id) {
        return ResponseEntity.ok(service.getAllCars(id));
    }

    @GetMapping("/motos/{id}")
    @CircuitBreaker(name = "getAllUsersMotoBreaker", fallbackMethod = "getAllUsersMotoFallback")
    public ResponseEntity<Optional<User>> getAllUsersMoto(@PathVariable String id) {
        return ResponseEntity.ok(service.getAllMotos(id));
    }

    public ResponseEntity<User> getAllUsersCarsFallback(String id, Exception exception) {
        User user = User.builder()
                .email("root@gmail.com")
                .nombre("root")
                .apellido("Usuario por defecto tras error")
                .id("1234")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<User> getAllUsersMotoFallback(String id, Exception exception) {
        User user = User.builder()
                .email("root@gmail.com")
                .nombre("root")
                .apellido("Usuario por defecto tras error")
                .id("1234")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<User> getAllUsersFallback(Exception exception) {
        User user = User.builder()
                .email("root@gmail.com")
                .nombre("root")
                .apellido("Usuario por defecto tras error")
                .id("1234")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
