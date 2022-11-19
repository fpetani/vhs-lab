package com.example.vhs.controller;

import com.example.vhs.entity.Rental;
import com.example.vhs.request.RentalForm;
import com.example.vhs.request.ReturnForm;
import com.example.vhs.service.RentalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rental")
public class RentalController {
    private final RentalService rentalService;

    private Logger log = LoggerFactory.getLogger(RentalController.class);

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
        log.debug("Request POST api/rental with body:{}", form);
        Rental rental = rentalService.createRental(form);
        ResponseEntity responseEntity = ResponseEntity.ok(rental);
        log.debug("Response {}", responseEntity);
        return responseEntity;
    }

    @PutMapping("/{id}")
    public ResponseEntity returnVHS(@Valid @RequestBody ReturnForm form, @PathVariable Long id){
        log.debug("Request PUT api/rental/{} with body:{}",id , form);
        Rental rental = rentalService.updateRental(id, form);
        ResponseEntity responseEntity = ResponseEntity.ok(rental);
        log.debug("Response {}", responseEntity);
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRental(@PathVariable Long id){
        log.debug("Request DELETE api/rental/{} with body:{}",id);
        Rental rental = rentalService.deleteRental(id);
        ResponseEntity responseEntity = ResponseEntity.ok(rental);
        log.debug("Response {}", responseEntity);
        return responseEntity;

    }




}
