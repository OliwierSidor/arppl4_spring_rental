package pl.sda.arppl4.spring_rental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.spring_rental.model.Car;
import pl.sda.arppl4.spring_rental.model.dto.CarDTO;
import pl.sda.arppl4.spring_rental.repository.CarRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<CarDTO> findAll() {
        List<Car> carList = carRepository.findAll();

        List<CarDTO> cars = new ArrayList<>();
        for (Car car : carList) {
            cars.add(car.mapToCarDTO());
        }

        return cars;
    }

    public List<Car> carList() {
        return carRepository.findAll();
    }

    public void addCar(Car car) {
        carRepository.save(car);
    }

    public void updateCar(Car car) {
        Long id = car.getId();
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car updatedCar = optionalCar.get();
            if (car.getName() != null) {
                updatedCar.setName(car.getName());
            }
            if (car.getProductionDate() != null) {
                updatedCar.setProductionDate(car.getProductionDate());
            }
            if (car.getCarBody() != null) {
                updatedCar.setCarBody(car.getCarBody());
            }
            if (car.getSeats() != null) {
                updatedCar.setSeats(car.getSeats());
            }
            if (car.getGearbox() != null) {
                updatedCar.setGearbox(car.getGearbox());
            }
            if (car.getCapacityEngine() != null) {
                updatedCar.setCapacityEngine(car.getCapacityEngine());
            }

            carRepository.save(updatedCar);
            log.info("Samochód został updatetowany");
            return;
        }
        throw new EntityNotFoundException("Nie znaleziono samochodu o id: " + id);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
