package org.imt.tournamentmaster.configuration.actuator;

import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.repository.match.MatchRepository;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * HealthIndicator custom pour vérifier l'activité des matchs.
 * 
 * Retourne DOWN si aucun match n'a été joué depuis plus d'un mois.
 * C'est utile pour détecter si le tournoi est "mort" ou inactif.
 */
@Component
public class MatchHealthIndicator implements HealthIndicator {

    private final MatchRepository matchRepository;

    public MatchHealthIndicator(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Health health() {
        // On regarde les matchs TERMINES joués dans le dernier mois
        LocalDateTime unMoisAvant = LocalDateTime.now().minusMonths(1);
        long matchsRecents = matchRepository.countByStatusAndDateJoueAfter(Match.Status.TERMINE, unMoisAvant);

        if (matchsRecents == 0) {
            // Aucun match joué depuis 1 mois = KO
            return Health.down()
                    .withDetail("message", "Aucun match joué depuis plus d'un mois")
                    .withDetail("derniere_verification", LocalDateTime.now())
                    .build();
        }

        // Au moins un match récent = OK
        return Health.up()
                .withDetail("matchs_ce_mois", matchsRecents)
                .withDetail("derniere_verification", LocalDateTime.now())
                .build();
    }
}
