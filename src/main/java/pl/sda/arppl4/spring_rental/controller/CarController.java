package pl.sda.arppl4.spring_rental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.spring_rental.model.Car;
import pl.sda.arppl4.spring_rental.model.dto.CarDTO;
import pl.sda.arppl4.spring_rental.service.CarService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/list")
    public List<CarDTO> findAll() {
        log.info("Wywołano metodę findAll");
        return carService.findAll();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCar(@RequestBody Car car) {
        log.info("Wywołano metodę addCar: " + car);
        carService.addCar(car);
    }

    @PatchMapping("/update")
    public void updateCar(@RequestBody Car car) {
        log.info("Wywołano metodę aktualizacje danych samochodu: " + car);
        carService.updateCar(car);
    }

    @DeleteMapping("/delete/{identifier}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable(name = "identifier") Long id) {
        log.info("Wywołano metodę deleteCar: " + id);
        carService.deleteCar(id);
    }

}
