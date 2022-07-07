package pl.sda.arppl4.spring_rental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.spring_rental.model.Car;
import pl.sda.arppl4.spring_rental.service.CarService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/list")
    public List<Car> carList() {
        log.info("Wywołano metodę carList");
        List<Car> allCars = carService.carList();
        return allCars;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCar(@RequestBody Car car) {
        log.info("Wywołano dodanie samochodu: " + car);
        carService.addCar(car);
    }

}
