package pl.sda.arppl4.spring_rental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    public Car(String name, String brand, LocalDate productionDate, CarBody carBody, Integer seats, Gearbox gearbox, Double capacityEngine) {
        this.name = name;
        this.brand = brand;
        this.productionDate = productionDate;
        this.carBody = carBody;
        this.seats = seats;
        this.gearbox = gearbox;
        this.capacityEngine = capacityEngine;
    }
}
