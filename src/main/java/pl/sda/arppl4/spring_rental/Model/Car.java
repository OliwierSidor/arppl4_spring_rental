package pl.sda.arppl4.spring_rental.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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

    private int seats;

    @Enumerated(EnumType.STRING)
    private Gearbox gearbox;

    private Double capacityEngine;
}
