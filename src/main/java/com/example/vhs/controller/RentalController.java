package com.example.vhs.controller;

import com.example.vhs.entity.Rental;
import com.example.vhs.request.RentalForm;
import com.example.vhs.request.ReturnForm;
import com.example.vhs.service.RentalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rental")
//@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
public class RentalController {
    private final RentalService rentalService;

    @Value("${my.greeting: default greeting}")
    private String greeting;

    public RentalController(RentalService rentalService){
        this.rentalService = rentalService;
    }

    @GetMapping("")
    public ResponseEntity getAllRental(){
        System.out.println(greeting);
        return ResponseEntity.ok(rentalService.findAllRental());
    }

    @GetMapping("/{id}")
    public ResponseEntity getRentalById(@PathVariable Long id){
        return ResponseEntity.ok(rentalService.findRentalById(id));
    }

    @PostMapping
    public ResponseEntity rentVHS(@Valid @RequestBody RentalForm form){
        Rental rental = rentalService.createRental(form);
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
