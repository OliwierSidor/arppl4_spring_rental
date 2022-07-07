package pl.sda.arppl4.spring_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.arppl4.spring_rental.Model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}