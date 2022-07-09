package pl.sda.arppl4.spring_rental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentCarRequest {
    private String surnameOfTheClient;
    private Double hourlyPrice;
    private String nameOfTheClient;
}
