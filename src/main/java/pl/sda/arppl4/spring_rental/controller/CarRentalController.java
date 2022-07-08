package pl.sda.arppl4.spring_rental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.spring_rental.model.Car;
import pl.sda.arppl4.spring_rental.model.CarRental;
import pl.sda.arppl4.spring_rental.service.CarRentalService;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/carrental")
@RequiredArgsConstructor
public class CarRentalController {
    private final CarRentalService carRentalService;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @GetMapping("/listofavailablecars")
    public List<Car> listOfAvailableCars() {
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
    public void rentCar(@RequestBody CarRental params){
        log.info("Wywołano metodę rentCar" + params);
        carRentalService.rentCar(params.getCar().getId(), params);
    }

    @PatchMapping("/return")
    public String returnCar(@RequestParam Long carId ){
        log.info("Wywołano metodę returnCar" + carId);
        Double price = carRentalService.returnCar(carId);
        return "You have to pay: " + df.format(price) + "ZŁ";
    }
}
