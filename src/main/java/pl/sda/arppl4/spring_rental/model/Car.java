package pl.sda.arppl4.spring_rental.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.sda.arppl4.spring_rental.model.dto.CarDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private LocalDate productionDate;

    @Enumerated(EnumType.STRING)
    private CarBody carBody;

    private Integer seats;

    @Enumerated(EnumType.STRING)
    private Gearbox gearbox;

    private Double capacityEngine;

    @OneToMany(mappedBy = "car")
    @EqualsAndHashCode.Exclude
    private Set<CarRental> carRentals;

    public CarDTO mapToCarDTO() {
        return new CarDTO(
                id,
                name,
                brand,
                productionDate,
                carBody,
                seats,
                gearbox,
                capacityEngine
        );
    }
}
