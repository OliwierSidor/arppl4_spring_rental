package pl.sda.arppl4.spring_rental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.arppl4.spring_rental.model.CarBody;
import pl.sda.arppl4.spring_rental.model.Gearbox;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Long carId;
    private String name;
    private String make;
    private LocalDate productionDate;
    private CarBody bodyType;
    private Integer seats;
    private Gearbox carGearBox;
    private Double engineCapacity;
}
