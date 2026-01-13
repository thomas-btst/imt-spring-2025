package org.imt.tournamentmaster.controller.equipe;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.imt.tournamentmaster.dto.equipe.CreateOrUpdateEquipeDto;
import org.imt.tournamentmaster.dto.equipe.EquipeDto;
import org.imt.tournamentmaster.service.equipe.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "basicAuth")
@RequestMapping("/api/equipe")
public class EquipeController {

    private final EquipeService equipeService;

    @Autowired
    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve an equipe by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", description = "Equipe not found")
    })
    public EquipeDto getById(@PathVariable long id) {
        return equipeService.getById(id);
    }

    @GetMapping
    @Operation(summary = "Retrieve all equipes")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
    })
    public List<EquipeDto> getAll() {
        return equipeService.getAll();
    }

    @PostMapping
    @Operation(summary = "Create an equipe")
    @ApiResponses({
        @ApiResponse(responseCode = "201"),
        @ApiResponse(responseCode = "404", description = "A joueur from the equipe does exist"),
        @ApiResponse(
            responseCode = "409",
            description = "Equipe with this name already exists " +
                "or a joueur is already affected to an equipe"
        )
    })
    @ResponseStatus(HttpStatus.CREATED)
    public EquipeDto createEquipe(@RequestBody @Valid CreateOrUpdateEquipeDto equipeDto) {
        return equipeService.createEquipe(equipeDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an equipe by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", description = "A joueur from the equipe does exist"),
        @ApiResponse(
            responseCode = "409",
            description = "Equipe not found " +
                "or Equipe with this name already exists " +
                "or a joueur is already affected to an equipe"
        )
    })
    public EquipeDto updateEquipe(@PathVariable long id, @RequestBody @Valid CreateOrUpdateEquipeDto equipeDto) {
        return equipeService.updateEquipe(id, equipeDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an equipe by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Equipe not found"),
            @ApiResponse(responseCode = "409", description = "A match with this equipe exists")
    })
    public EquipeDto deleteEquipe(@PathVariable long id) {
        return equipeService.deleteEquipe(id);
    }
}
