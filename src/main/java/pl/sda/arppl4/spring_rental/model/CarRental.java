package pl.sda.arppl4.spring_rental.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CarRental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private String clientSurname;

    private LocalDateTime rentDateTime;
    private LocalDateTime returnDateTime;
    private Double priceForH;

    @ManyToOne
    @ToString.Exclude
    private Car car;

    public CarRental(String clientName, String clientSurname, LocalDateTime rentDateTime, Double priceForH, Car car) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.rentDateTime = rentDateTime;
        this.priceForH = priceForH;
        this.car = car;
    }
}

