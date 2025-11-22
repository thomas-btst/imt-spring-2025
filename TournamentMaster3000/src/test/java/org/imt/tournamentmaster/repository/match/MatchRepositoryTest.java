package org.imt.tournamentmaster.repository.match;

import org.imt.tournamentmaster.model.match.Match;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MatchRepositoryTest {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(MatchRepositoryTest.class);

    @Autowired
    private MatchRepository matchRepository;

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
