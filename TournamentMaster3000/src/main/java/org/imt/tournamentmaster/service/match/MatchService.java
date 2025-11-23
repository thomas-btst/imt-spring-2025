package org.imt.tournamentmaster.service.match;

import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.repository.match.MatchRepository;

import java.util.List;

public class MatchService {

    // TODO-02 : Injecter le repository dans ce service, et injecter ce service dans le contexte de beans Sring à son tour

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match getById(int id) {
        return matchRepository.findById(id);
    }

    public List<Match> getAll() {
        return matchRepository.findAll();
    }
}
