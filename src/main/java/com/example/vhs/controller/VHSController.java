package com.example.vhs.controller;

import com.example.vhs.entity.VHS;
import com.example.vhs.exception.ApiRequestException;
import com.example.vhs.service.VHSService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/vhs")
//@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
public class VHSController {

    private final VHSService vhsService;

    public VHSController(VHSService vhsService){
        this.vhsService = vhsService;
    }

    @GetMapping("")
    public ResponseEntity getAllVHS(){
        return ResponseEntity.ok(this.vhsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VHS> findById(@PathVariable Long id){
        return ResponseEntity.ok(vhsService.findById(id));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<VHS>> findByTitle(@PathVariable @NotBlank String title){
        return ResponseEntity.ok(vhsService.filterByTitle(title));
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<VHS>> findByYear(@Valid @PathVariable @NotBlank @Min(1900) @Max(2023) Integer year){
        return ResponseEntity.ok(vhsService.filterByYear(year));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<VHS>> findByGenre(@PathVariable @NotBlank String genre){
        return ResponseEntity.ok(vhsService.filterByGenre(genre));
    }


}
