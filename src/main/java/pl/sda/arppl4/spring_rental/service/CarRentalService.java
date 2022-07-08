package pl.sda.arppl4.spring_rental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.spring_rental.model.Car;
import pl.sda.arppl4.spring_rental.model.CarRental;
import pl.sda.arppl4.spring_rental.repository.CarRentalRepository;
import pl.sda.arppl4.spring_rental.repository.CarRepository;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarRentalService {
    private final CarRentalRepository carRentalRepository;
    private final CarRepository carRepository;

    public List<Car> carAvailableList() {
        List<Car> carList = carRepository.findAll();
        List<Car> availableCars = new ArrayList<>();
        for (Car car : carList) {
            if (!isRented(car)) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    private boolean isRented(Car car) {
        for (CarRental carRental : car.getCarRentals()) {
            if (carRental.getReturnDateTime() == null) {
                return true;
            }
        }
        return false;
    }


    public Optional<Car> getCar(Long id, boolean isRented) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car availableCar = optionalCar.get();
            if (isRented(availableCar) == isRented) {
                return Optional.of(availableCar);
            }
        }
        return Optional.empty();
    }

    public void rentCar(Long carId, CarRental params) {
        Optional<Car> carToRent = getCar(carId, false);
        if (carToRent.isPresent()) {
            CarRental newRental = new CarRental(params);
            carRentalRepository.save(newRental);
        }
    }

    public double returnCar(Long carId) {
        Optional<Car> carToRent = getCar(carId, true);
        if (carToRent.isPresent()) {
            Set<CarRental> rentals = carToRent.get().getCarRentals();
            for (CarRental rental : rentals) {
                if (rental.isRented()) {
                    rental.setReturnDateTime(LocalDateTime.now());
                    carRentalRepository.save(rental);
                    double seconds = ChronoUnit.SECONDS.between(rental.getRentDateTime(), rental.getReturnDateTime());
                    double hours = seconds/3600d;
                    return rental.getPrice() * hours;
                }
            }
        }
        throw new RuntimeException("Not found");
    }
}
// Szukasz obiekt w bazie
// Po znalezieniu (upewnij się że jest) wypakuj
// Stwórz nowy najem (nowy rekord/obiekt) najmu
// CarRental musi zawierać: imie, nazwisko, cena
// NIE BĘDZIE NIGDY WYNAJMU BEZ USTAWIONEJ DATY STARTU
// OD tego momentu istnieje zmienna / obiekt carRental
// Do CarRental przypisujesz (setCar()) samochód który wcześniej wypakowales
// zapisuejsz do bazy car rental.

// Szukasz obiekt w bazie
// Po znalezieniu (upewnij się że jest) wypakuj
// Przeszukaj listę w poszukiwaniu najmu (na liiście powiązanej z samochod) który nie jest zamkniety
// po znalezieniu takiego najmu ustaw mu date zakonczenia na now()
// zapisuejsz do bazy car rental.