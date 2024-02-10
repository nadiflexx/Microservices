package authservice.car.service.service.implementation;

import authservice.car.service.entity.Car;
import authservice.car.service.exceptions.DataNotFoundException;
import authservice.car.service.repository.CarRepository;
import authservice.car.service.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImplementation implements CarService {

    @Autowired
    private CarRepository repository;

    @Override
    public Car saveCar(Car car) {
        return repository.save(car);
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> cars = repository.findAll();
        if (cars.isEmpty()) throw new DataNotFoundException("No cars found");
        return repository.findAll();
    }

    @Override
    public Optional<Car> getCarById(String id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new DataNotFoundException("Car not found " + id)));
    }

    @Override
    public Car updateCar(Car car) {
        return repository.save(car);
    }

    @Override
    public void deleteCar(String id) {
        repository.findById(id).orElseThrow(() -> new DataNotFoundException("Car not found " + id));
        repository.deleteById(id);
    }

    @Override
    public List<Car> findByUserId(String userId) {
        return repository.findCarByUserId(userId);
    }
}
