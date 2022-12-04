package com.bochi.fairapi.fair.web;

import com.bochi.fairapi.fair.FairService;
import com.bochi.fairapi.fair.dto.FairResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/fair/api")
public class FairRestService {

    private final FairService fairService;

    @PostMapping(value = "", produces = "application/json")
    FairResponseDTO create() {
        return null;
    }

    @GetMapping(value = "", produces = "application/json")
    FairResponseDTO findByName() {
        return null;
    }

    @GetMapping(value = "", produces = "application/json")
    FairResponseDTO findAllByFilter() {
        return null;
    }

    @PutMapping(value = "", produces = "application/json")
    FairResponseDTO update() {
        return null;
    }

    @PatchMapping(value = "", produces = "application/json")
    FairResponseDTO partialUpdate() {
        return null;
    }

    @DeleteMapping(value = "", produces = "application/json")
    void delete() {

    }

    @DeleteMapping(value = "", produces = "application/json")
    void inactivate() {

    }

}
