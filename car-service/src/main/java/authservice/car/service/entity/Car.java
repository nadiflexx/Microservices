package authservice.car.service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("car")
public class Car {

    @Id
    private String id;
    private String brand;
    private String model;
    private String userId;
}