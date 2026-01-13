package org.imt.tournamentmaster.controller.equipe;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.imt.tournamentmaster.dto.joueur.CreateOrUpdateJoueurDto;
import org.imt.tournamentmaster.dto.joueur.JoueurDto;
import org.imt.tournamentmaster.service.equipe.JoueurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "basicAuth")
@RequestMapping("/api/joueur")
public class JoueurController {

    private final JoueurService joueurService;

    @Autowired
    public JoueurController(JoueurService joueurService) {
        this.joueurService = joueurService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a joueur by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", description = "Joueur not found")
    })
    public JoueurDto getJoueurById(@PathVariable Long id) {
        return joueurService.getById(id);
    }

    @GetMapping
    @Operation(summary = "Retrieve all joueurs")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
    })
    public List<JoueurDto> getAllJoueurs() {
        return joueurService.getAll();
    }

    @PostMapping
    @Operation(summary = "Create a joueur")
    @ApiResponses({
        @ApiResponse(responseCode = "201"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    public JoueurDto createJoueur(@RequestBody @Valid CreateOrUpdateJoueurDto joueurDto) {
        return joueurService.createJoueur(joueurDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a joueur by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", description = "Joueur not found")
    })
    public JoueurDto updateJoueur(@PathVariable Long id, @RequestBody @Valid CreateOrUpdateJoueurDto joueurDto) {
        return joueurService.updateJoueur(id, joueurDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a joueur by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Joueur not found"),
            @ApiResponse(responseCode = "409", description = "Joueur is assigned to an equipe")
    })
    public JoueurDto deleteJoueur(@PathVariable Long id) {
        return joueurService.deleteJoueur(id);
    }
}
