package authservice.car.service.repository;

import authservice.car.service.entity.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends MongoRepository<Car, String> {
    List<Car> findCarByUserId(String userId);

}