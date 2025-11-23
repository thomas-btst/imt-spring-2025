package org.imt.tournamentmaster.controller.match;

import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.service.match.MatchService;

import java.util.List;

public class MatchController {

    // TODO-03a : Injecter le service dans ce controller
    // TODO-03b : Lancer l'application. Elle doit démarrer, mais il ne se passe rien en allant sur http://localhost:8080, pourquoi ?

    // TODO-04a : Modifier ce controller pour qu'il s'expose sur le chemin /api/match
    // TODO-04b : Modifier la méthode getAll() pour qu'elle soit exposée en GET sur le chemin /api/match
    // TODO-04c : Modifier la méthode getById() pour qu'elle soit exposée en GET sur le chemin /api/match/{id} avec l'annotation @PathVariable

    // TODO-05 : Lancer l'application et aller sur http://localhost:8080/api/match et sur http://localhost:8080/api/match/1, constater le résultat et voir la différence entre les deux

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    public Match getById(int id) {
        return matchService.getById(id);
    }

    public List<Match> getAll() {
        return matchService.getAll();
    }
}
