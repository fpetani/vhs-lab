package com.example.vhs.controller;

import com.example.vhs.entity.Rental;
import com.example.vhs.request.RentalForm;
import com.example.vhs.request.ReturnForm;
import com.example.vhs.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {
    private RentalService rentalService;

    public RentalController(RentalService rentalService){
        this.rentalService = rentalService;
    }

    @GetMapping("")
    public ResponseEntity getAllRental(){
        return ResponseEntity.ok(rentalService.findAllRental());
    }

    @GetMapping("/{id}")
    public ResponseEntity getRentalById(@PathVariable Long id){
        return ResponseEntity.ok(rentalService.findRentalById(id));
    }

    @PostMapping
    public ResponseEntity rentVHS(@Valid @RequestBody RentalForm form){
        Rental rental = rentalService.createRental(form);
        //TODO handle exception if vhs is already rented
        if(rental.getRentalDate().equals(null)){
            //return ResponseEntityExceptionHandler
        }
        return ResponseEntity.ok(rental);
    }

    @PutMapping("/{id}")
    public ResponseEntity returnVHS(@Valid @RequestBody ReturnForm form, @PathVariable Long id){
        Rental rental = rentalService.updateRental(id, form);
        return ResponseEntity.ok(rental);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRental(@PathVariable Long id){
        return ResponseEntity.ok(rentalService.deleteRental(id));
    }


}
