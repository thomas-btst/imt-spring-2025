package org.imt.tournamentmaster.repository.match;

import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.repository.equipe.EquipeRepository;
import org.imt.tournamentmaster.repository.equipe.EquipeRepositoryImpl;
import org.imt.tournamentmaster.repository.equipe.JoueurRepository;
import org.imt.tournamentmaster.repository.equipe.JoueurRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.util.List;

public class MatchRepositoryTest {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(MatchRepositoryTest.class);

    private final JoueurRepository joueurRepository = new JoueurRepositoryImpl();

    private final EquipeRepository equipeRepository = new EquipeRepositoryImpl(joueurRepository);

    private final RoundRepository roundRepository = new RoundRepositoryImpl();

    private final MatchRepository matchRepository = new MatchRepositoryImpl(equipeRepository, roundRepository);

    @Test
    public void testFindById() {
        // find a match
        Match match = matchRepository.findById(1L);

        // assert
        Assertions.assertNotNull(match);
        Assertions.assertEquals(1L, match.getId());

        Assertions.assertNotNull(match.getEquipeA());
        Assertions.assertEquals(1L, match.getEquipeA().getId());

        Assertions.assertNotNull(match.getEquipeB());
        Assertions.assertEquals(2L, match.getEquipeB().getId());

        Assertions.assertNotNull(match.getRounds());
        Assertions.assertEquals(3, match.getRounds().size());

        Assertions.assertEquals(Match.Status.TERMINE, match.getStatus());

        Assertions.assertEquals(match.getEquipeA(), match.determineWinner());
    }

    @Test
    public void testFindAll() {
        // find all matchs
        List<Match> matchs = matchRepository.findAll();

        // assert
        Assertions.assertNotNull(matchs);
        Assertions.assertEquals(1, matchs.size());
    }

}
