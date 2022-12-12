package com.backend.fairapi.presentation.web;

import com.backend.fairapi.presentation.dto.*;
import com.backend.fairapi.presentation.FairService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;

/**
 * The Fair Rest Controller.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/fair/api")
public class FairRestController {

    private final FairService fairService;

    /**
     * Creates a new fair.
     *
     * @param createDTO the create dto
     * @return the fair response dto
     */
    @Operation(
            summary = "Cadastra uma Feira",
            description = "Cadastra uma nova Feira, baseada nos campos enviados no createDTO",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201"),
                    @ApiResponse(description = "Not authorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            }
    )
    @ResponseStatus(CREATED)
    @PostMapping(value = "/", produces = "application/json")
    FairResponseDTO create(@RequestBody FairCreateDTO createDTO) {
        return FairResponseDTO.of(fairService.create(createDTO));
    }

    /**
     * Finds a fair by register code.
     *
     * @param registerCode the register code
     * @return the fair response dto
     */
    @Operation(
            summary = "Busca uma Feira pelo registro",
            description = "Busca uma feira baseada no campo registro",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201"),
                    @ApiResponse(description = "Entity not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            }
    )
    @ResponseStatus(OK)
    @GetMapping(value = "/{registerCode}", produces = "application/json")
    FairResponseDTO findByRegisterCode(@PathVariable String registerCode) {
        return FairResponseDTO.of(fairService.getByRegisterCode(registerCode));
    }

    /**
     * Finds page(s) of fair, filtered by the params.
     *
     * @param page          the page number
     * @param size          the size of the page
     * @param district      the district
     * @param region5       the region5
     * @param fairName      the fair_name
     * @param neighbourhood the neighbourhood
     * @return the page of fairs filtered
     */
    @Operation(
            summary = "Busca uma página de Feiras por filtro",
            description = "Busca uma página de feiras baseado no filtro de dristrito, regiao5, nome e bairro",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201"),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            }
    )
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

    /**
     * Updates a fair.
     *
     * @param registerCode the register code of the fair
     * @param updateDTO    the update dto
     * @return the fair response dto
     */
    @Operation(
            summary = "Atualiza uma Feira",
            description = "Atualiza uma Feira, buscando-a pelo registro",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201"),
                    @ApiResponse(description = "Entity not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            }
    )
    @ResponseStatus(OK)
    @PutMapping(value = "/{registerCode}", produces = "application/json")
    FairResponseDTO update(@PathVariable String registerCode,
                           @RequestBody FairUpdateDTO updateDTO) {
        return FairResponseDTO.of(fairService.update(registerCode, updateDTO));
    }

    /**
     * Updates one field of the fair.
     *
     * @param registerCode the register code
     * @param partialDTO   the partial dto specifying the field and the value
     * @return the fair response dto
     */
    @Operation(
            summary = "Atualiza parcialmente uma Feira",
            description = "Atualiza apenas um campo de uma Feira, buscando-a pelo registro",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201"),
                    @ApiResponse(description = "Entity not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            }
    )
    @ResponseStatus(OK)
    @PatchMapping(value = "/{registerCode}", produces = "application/json")
    FairResponseDTO partialUpdate(@PathVariable String registerCode,
                                  @RequestBody FairPartialDTO partialDTO) {
        return FairResponseDTO.of(fairService.updatePartial(registerCode, partialDTO));
    }

    /**
     * Delete a fair.
     *
     * @param registerCode the register code
     */
    @Operation(
            summary = "Exclui uma Feira",
            description = "Exclui uma Feira, buscando-a pelo registro",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201"),
                    @ApiResponse(description = "Entity not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            }
    )
    @ResponseStatus(OK)
    @DeleteMapping(value = "/{registerCode}", produces = "application/json")
    void delete(@PathVariable String registerCode) {
        fairService.delete(registerCode);
    }

}
