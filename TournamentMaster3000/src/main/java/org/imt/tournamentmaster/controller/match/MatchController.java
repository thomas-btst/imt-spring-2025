package org.imt.tournamentmaster.controller.match;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.imt.tournamentmaster.dto.match.MatchStatusDto;
import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.service.match.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.imt.tournamentmaster.dto.equipe.CreateOrUpdateEquipeDto;
import org.imt.tournamentmaster.dto.equipe.EquipeDto;
import org.imt.tournamentmaster.dto.match.CreateMatchDto;
import org.imt.tournamentmaster.dto.match.MatchDto;

import aj.org.objectweb.asm.Label;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a match by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Match not found")
    })
    public MatchDto getById(@PathVariable Long id) {
        return matchService.getById(id);
    }

    @GetMapping
    @Operation(summary = "Retrieve all matches")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
    })
    public List<MatchDto> getAll() {
        return matchService.getAll();
    }

     // 1.CS Endpoint de recherche avec critères optionnels
    @GetMapping("/search")
    @Operation(summary = "Search matches by criteria", description = "Search matches by status and/or team. All parameters are optional.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of matching matches"),
    })
    public List<MatchDto> search(
            @Parameter(description = "Match status (NOUVEAU, EN_COURS, TERMINE)") 
            @RequestParam(required = false) Match.Status status,
            @Parameter(description = "Team ID (recherche dans both equipeA and equipeB)") 
            @RequestParam(required = false) Long equipeId
    ) {
        return matchService.search(status, equipeId);
    }

    @PostMapping
    @Operation(summary = "Create a match")
    @ApiResponses({
        @ApiResponse(responseCode = "201"),
        @ApiResponse(responseCode = "400", description = "Equipe A should not be equal to equipe B"),
        @ApiResponse(responseCode = "404", description = "An equipe from the match doesn't exist"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    public MatchDto createMatch(@RequestBody @Valid CreateMatchDto matchDto) {
        return matchService.createMatch(matchDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update a match status by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "400", description = "The new status can't be applied"),
        @ApiResponse(responseCode = "404", description = "Match not found"),
    })
    public MatchDto updateMatchStatus(@PathVariable Long id, @RequestBody @Valid MatchStatusDto statusDto) {
        return matchService.updateMatchStatus(id, statusDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a match by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", description = "Match not found"),
    })
    public MatchDto deleteMatch(@PathVariable Long id) {
        return matchService.deleteMatch(id);
    }
}
