package pl.sda.arppl4.spring_rental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.spring_rental.exception.CarNotAvailableException;
import pl.sda.arppl4.spring_rental.model.Car;
import pl.sda.arppl4.spring_rental.model.CarRental;
import pl.sda.arppl4.spring_rental.model.dto.CarDTO;
import pl.sda.arppl4.spring_rental.model.dto.RentCarRequest;
import pl.sda.arppl4.spring_rental.repository.CarRentalRepository;
import pl.sda.arppl4.spring_rental.repository.CarRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
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

    public List<CarDTO> carAvailableList() {
        List<Car> carList = carRepository.findAll();
        List<CarDTO> availableCars = new ArrayList<>();
        for (Car car : carList) {
            if (!isRented(car)) {
                availableCars.add(car.mapToCarDTO());
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

    public void rentCar(Long carId, RentCarRequest request) {
        Optional<Car> carToRent = carRepository.findById(carId);
        if (carToRent.isPresent()) {
            Car car = carToRent.get();
            if (!isRented(car)) {
                CarRental carRental = mapRentCarRequestToCarRental(request);
                carRental.setCar(car);
                carRentalRepository.save(carRental);
                return;
            }
            throw new CarNotAvailableException("Car not available, id: " + carId);
        }
        throw new EntityNotFoundException("Unable to find car with id: " + carId);
    }

    private CarRental mapRentCarRequestToCarRental(RentCarRequest request) {
        return new CarRental(
                request.getNameOfTheClient(),
                request.getSurnameOfTheClient(),
                request.getHourlyPrice());
    }

    public void returnCar(Long carId) {
        Optional<Car> carToReturn = getCar(carId, true);
        if (carToReturn.isPresent()) {
            Set<CarRental> rentals = carToReturn.get().getCarRentals();
            for (CarRental rental : rentals) {
                if (rental.isRented()) {
                    rental.setReturnDateTime(LocalDateTime.now());
                    carRentalRepository.save(rental);
                    return;
                }
            }
        }
        throw new CarNotAvailableException("Car not available, id:");
    }
}
