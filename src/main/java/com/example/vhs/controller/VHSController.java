package com.example.vhs.controller;

import com.example.vhs.entity.VHS;
import com.example.vhs.service.VHSService;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
import org.slf4j.Logger;
@RestController
@RequestMapping("/vhs")
public class VHSController {

    private final VHSService vhsService;

    private final Logger log = LoggerFactory.getLogger(VHSController.class);

    public VHSController(VHSService vhsService){
        this.vhsService = vhsService;
    }

    @GetMapping("")
    public ResponseEntity<List<VHS>> getAllVHS(){
        log.debug("Request GET api/vhs");
        ResponseEntity<List<VHS>> responseEntity = ResponseEntity.ok(this.vhsService.findAll());
        log.debug("Response {}", responseEntity);
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VHS> findById(@PathVariable Long id){
        log.debug("Request GET api/vhs/{}", id);
        ResponseEntity<VHS> responseEntity = ResponseEntity.ok(vhsService.findById(id));
        log.debug("Response {}", responseEntity);
        return responseEntity;
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<VHS>> findByTitle(@PathVariable @NotBlank String title){
        log.debug("Request GET api/vhs/title/{}", title);
        ResponseEntity<List<VHS>> responseEntity = ResponseEntity.ok(vhsService.filterByTitle(title));
        log.debug("Response {}", responseEntity);
        return responseEntity;
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<VHS>> findByYear(@Valid @PathVariable @Min(1900) @Max(2023) Integer year){
        log.debug("Request GET api/vhs/year/{}", year);
        ResponseEntity<List<VHS>> responseEntity = ResponseEntity.ok(vhsService.filterByYear(year));
        log.debug("Response {}", responseEntity);
        return responseEntity;
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<VHS>> findByGenre(@PathVariable @NotBlank String genre){
        log.debug("Request GET api/vhs/genre/{}", genre);
        ResponseEntity<List<VHS>> responseEntity = ResponseEntity.ok(vhsService.filterByGenre(genre));
        log.debug("Response {}", responseEntity);
        return responseEntity;
    }


}
