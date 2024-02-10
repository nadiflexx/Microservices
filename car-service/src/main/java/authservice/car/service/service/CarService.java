package authservice.car.service.service;

import authservice.car.service.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Car saveCar(Car user);

    List<Car> getAllCars();

    Optional<Car> getCarById(String id);

    Car updateCar(Car user);

    void deleteCar(String id);

    List<Car> findByUserId(String userId);
}
