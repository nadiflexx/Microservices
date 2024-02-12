package authservice.usuario.service.external.services;

import authservice.usuario.service.models.Motorcycle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "motorcycle-service")
public interface MotorcycleService {
    @GetMapping("/moto/user/{userId}")
    List<Motorcycle> getMotosByUserId(@PathVariable String userId);

}
