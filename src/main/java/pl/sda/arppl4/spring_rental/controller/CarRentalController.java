package pl.sda.arppl4.spring_rental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.spring_rental.model.Car;
import pl.sda.arppl4.spring_rental.model.dto.CarDTO;
import pl.sda.arppl4.spring_rental.model.dto.RentCarRequest;
import pl.sda.arppl4.spring_rental.service.CarRentalService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/carrental")
@RequiredArgsConstructor
public class CarRentalController {
    private final CarRentalService carRentalService;

    @GetMapping("/listofavailablecars")
    public List<CarDTO> listOfAvailableCars() {
        log.info("Wywołano metodę listOfAvailableCars");
        return carRentalService.carAvailableList();
    }

    @GetMapping("/showavailablecar")
    public Optional<Car> getCar(@RequestParam Long carId) {
        log.info("Wywołano metodę shoCar " + carId);
        return carRentalService.getCar(carId, false);
    }
    // @PathVariable {paramteru} <- wczytanie parametru ze scieżki
    // @RequestBody <- ciało zapytania (generated-requests)

    @PatchMapping("/rent")
    public void rentCar(@RequestParam Long carId, @RequestBody RentCarRequest request){
        log.info("Wywołano metodę rentCar" + carId);
        carRentalService.rentCar(carId, request);
    }

    @PatchMapping("/return")
    public void returnCar(@RequestParam Long carId ){
        log.info("Wywołano metodę returnCar" + carId);
        carRentalService.returnCar(carId);
    }
}
