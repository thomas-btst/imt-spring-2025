package org.imt.tournamentmaster.repository.match;

import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.match.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
    boolean existsByEquipeAOrEquipeB(Equipe equipeA, Equipe equipeB);
}
