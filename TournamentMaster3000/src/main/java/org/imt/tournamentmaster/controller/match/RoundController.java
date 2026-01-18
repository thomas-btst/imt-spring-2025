package org.imt.tournamentmaster.controller.match;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.imt.tournamentmaster.dto.round.CreateRoundDto;
import org.imt.tournamentmaster.dto.round.RoundDto;
import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.service.match.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/round")
public class RoundController {

    private final RoundService roundService;

    @Autowired
    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a round by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Round not found")
    })
    public RoundDto getById(@PathVariable long id) {
        return roundService.getById(id);
    }

    @GetMapping
    @Operation(summary = "Retrieve all the rounds")
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    public List<RoundDto> getAll() {
        return roundService.getAll();
    }

    @GetMapping("/max/{scoreA}")
    @Operation(summary = "Get all the rounds with a score A greater than a value")
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    public List<RoundDto> getMaxScore(@PathVariable int scoreA) {
       return roundService.getByScoreAGreaterThanEqual(scoreA);
    }

    @PostMapping
    @Operation(summary = "Create a new round")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Match does not exist.")
    })
    public RoundDto createRound(@RequestBody @Valid CreateRoundDto roundDto) {
        return roundService.createRound(roundDto);
    }
}
