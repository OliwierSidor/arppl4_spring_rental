package pl.sda.arppl4.spring_rental.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.sda.arppl4.spring_rental.exception.CarNotAvailableException;
import pl.sda.arppl4.spring_rental.model.dto.ErrorMessageResponse;

import javax.persistence.EntityNotFoundException;

@Slf4j
@ControllerAdvice
public class ErrorHandlingController{

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> handleEntityNotFound(EntityNotFoundException exception){
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessageResponse(exception.getMessage()));
    }

    @ExceptionHandler(CarNotAvailableException.class)
    public ResponseEntity<ErrorMessageResponse> handleCarNotAvailable(CarNotAvailableException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessageResponse(exception.getMessage()));
    }
}
