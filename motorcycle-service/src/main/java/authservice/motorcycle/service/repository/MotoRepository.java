package authservice.motorcycle.service.repository;

import authservice.motorcycle.service.entity.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MotoRepository extends JpaRepository<Moto, String> {
    List<Moto> findByUserId(String userId);
}