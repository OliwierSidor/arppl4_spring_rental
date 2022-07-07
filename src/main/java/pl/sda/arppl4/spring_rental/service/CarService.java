package pl.sda.arppl4.spring_rental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.spring_rental.model.Car;
import pl.sda.arppl4.spring_rental.repository.CarRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<Car> carList() {
        return carRepository.findAll();
    }

    public void addCar(Car car) {
        carRepository.save(car);
    }
}
