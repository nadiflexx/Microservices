package authservice.usuario.service.entity;


import javax.persistence.*;

import authservice.usuario.service.models.Car;
import authservice.usuario.service.models.Motorcycle;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nombre", length = 20)
    private String nombre;

    @Column(name = "apellido", length = 40)
    private String apellido;

    @Column(name = "email", length = 50)
    private String email;

    @Transient
    private List<Car> carsList = new ArrayList<>();

    @Transient
    private List<Motorcycle> motoList = new ArrayList<>();
}