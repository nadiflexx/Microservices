package authservice.motorcycle.service.service.implementation;

import authservice.motorcycle.service.entity.Moto;
import authservice.motorcycle.service.exceptions.DataNotFoundException;
import authservice.motorcycle.service.repository.MotoRepository;
import authservice.motorcycle.service.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MotoServiceImplementation implements MotoService {

    @Autowired
    private MotoRepository repository;


    @Override
    public Moto saveMoto(Moto moto) {
        moto.setId(UUID.randomUUID().toString());
        return repository.save(moto);
    }

    @Override
    public List<Moto> getAllMotos() {
        List<Moto> motos = repository.findAll();
        if (motos.isEmpty()) throw new DataNotFoundException("No motos found");
        return repository.findAll();
    }

    @Override
    public Optional<Moto> getMotoById(String id) {
        return Optional.of(repository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found " + id)));
    }

    @Override
    public Moto updateMoto(Moto moto) {
        return repository.save(moto);
    }

    @Override
    public void deleteMoto(String id) {
        repository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found " + id));
        repository.deleteById(id);
    }

    @Override
    public List<Moto> findByUserId(String userId) {
        return repository.findByUserId(userId);
    }
}
