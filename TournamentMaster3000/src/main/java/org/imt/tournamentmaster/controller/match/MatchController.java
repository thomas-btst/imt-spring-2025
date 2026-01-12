package org.imt.tournamentmaster.controller.match;

import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.service.match.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getById(@PathVariable long id) {
        Optional<Match> match = matchService.getById(id);

        return match.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Match> getAll() {
        return matchService.getAll();
    }

    // Endpoint de recherche avec critères
    // Exemples d'utilisation :
    //   GET /api/match/search                         -> tous les matchs
    //   GET /api/match/search?status=EN_COURS         -> matchs en cours
    //   GET /api/match/search?equipeId=1              -> matchs de l'équipe 1
    //   GET /api/match/search?status=TERMINE&equipeId=2 -> matchs terminés de l'équipe 2
    @GetMapping("/search")
    public List<Match> search(
            @RequestParam(required = false) Match.Status status,
            @RequestParam(required = false) Long equipeId
    ) {
        return matchService.search(status, equipeId);
    }
}