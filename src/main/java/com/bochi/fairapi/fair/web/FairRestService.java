package com.bochi.fairapi.fair.web;

import com.bochi.fairapi.fair.FairService;
import com.bochi.fairapi.fair.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
@RequestMapping("/fair/api")
public class FairRestService {

    private final FairService fairService;

    @ResponseStatus(CREATED)
    @PostMapping(produces = "application/json")
    FairResponseDTO create(@RequestBody FairCreateDTO createDTO) {
        return FairResponseDTO.of(fairService.create(createDTO));
    }

    @ResponseStatus(OK)
    @GetMapping(value = "/{name}", produces = "application/json")
    FairResponseDTO findByName(@PathVariable String name) {
        return FairResponseDTO.of(fairService.getByName(name));
    }

    @ResponseStatus(OK)
    @GetMapping(produces = "application/json")
    Page<FairResponseDTO> findAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                                  @RequestParam(defaultValue = "20", required = false) Integer size,
                                  @RequestParam(required = false) String district,
                                  @RequestParam(required = false) String region5,
                                  @RequestParam(required = false) String fairName,
                                  @RequestParam(required = false) String neighbourhood) {
        FairFilter filter = FairFilter.builder()
                .district(district)
                .region5(region5)
                .fairName(fairName)
                .neighbourhood(neighbourhood)
                .build();

        return fairService.findAllByFilter(PageRequest.of(page, size), filter)
                .map(FairResponseDTO::of);
    }

    @ResponseStatus(OK)
    @PutMapping(value = "/{registerCode}", produces = "application/json")
    FairResponseDTO update(@PathVariable String registerCode,
                           @RequestBody FairUpdateDTO updateDTO) {
        return FairResponseDTO.of(fairService.update(registerCode, updateDTO));
    }

    @ResponseStatus(OK)
    @PatchMapping(value = "/{registerCode}", produces = "application/json")
    FairResponseDTO partialUpdate(@PathVariable String registerCode,
                                  @RequestBody FairPartialDTO partialDTO) {
        return FairResponseDTO.of(fairService.updatePartial(registerCode, partialDTO));
    }

    @ResponseStatus(OK)
    @DeleteMapping(value = "/{registerCode}", produces = "application/json")
    void delete(@PathVariable String registerCode) {
        fairService.delete(registerCode);
    }

    @ResponseStatus(OK)
    @DeleteMapping(value = "/{registerCode}/inactivate", produces = "application/json")
    void inactivate(@PathVariable String registerCode) {
        fairService.softDelete(registerCode);
    }

}
