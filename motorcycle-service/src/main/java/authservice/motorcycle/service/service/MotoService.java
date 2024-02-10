package authservice.motorcycle.service.service;

import authservice.motorcycle.service.entity.Moto;

import java.util.List;
import java.util.Optional;

public interface MotoService {
    Moto saveMoto(Moto moto);

    List<Moto> getAllMotos();

    Optional<Moto> getMotoById(String id);

    Moto updateMoto(Moto moto);

    void deleteMoto(String id);

    List<Moto> findByUserId(String userId);
}
