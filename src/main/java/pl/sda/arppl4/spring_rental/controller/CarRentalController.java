package pl.sda.arppl4.spring_rental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.arppl4.spring_rental.service.CarRentalService;

@Slf4j
@RestController
@RequestMapping("/api/carrental")
@RequiredArgsConstructor
public class CarRentalController {
    private final CarRentalService carRentalService;

}
