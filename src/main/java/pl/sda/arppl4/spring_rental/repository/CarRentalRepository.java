package pl.sda.arppl4.spring_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.arppl4.spring_rental.model.CarRental;

public interface CarRentalRepository extends JpaRepository<CarRental, Long> {

}
