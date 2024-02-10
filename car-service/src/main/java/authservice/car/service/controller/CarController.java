package authservice.car.service.controller;

import authservice.car.service.entity.Car;
import authservice.car.service.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("car")
@AllArgsConstructor
public class CarController {

    @Autowired
    private CarService service;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Car> saveCar(@RequestBody Car car) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCar(car));
    }

    @GetMapping
    public List<Car> getAllCars() {
        return service.getAllCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable String id) {
        return service.getCarById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable String id, @RequestBody Car car) {
        return service.getCarById(id)
                .map(carDesired -> {
                    carDesired.setId(id);
                    carDesired.setBrand(car.getBrand());
                    carDesired.setModel(car.getModel());

                    Car userUpdate = service.updateCar(carDesired);
                    return new ResponseEntity<>(userUpdate, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCarById(@PathVariable String id) {
        service.deleteCar(id);
        return new ResponseEntity<>("Car deleted succesfully", HttpStatus.OK);
    }
}
