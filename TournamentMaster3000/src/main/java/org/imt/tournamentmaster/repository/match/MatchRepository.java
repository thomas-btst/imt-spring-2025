package org.imt.tournamentmaster.repository.match;

import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.match.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


// Query Method Spring Data
@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

    // Méthode existante de l'équipe
    boolean existsByEquipeAOrEquipeB(Equipe equipeA, Equipe equipeB);

    // 2.CS SCherche les matchs par statut (NOUVEAU, EN_COURS, TERMINE)
    List<Match> findByStatus(Match.Status status);

    // Cherche les matchs où une équipe joue (peu importe si elle est A ou B)
    List<Match> findByEquipeAIdOrEquipeBId(Long equipeAId, Long equipeBId);

    // Cherche les matchs par statut ET où une équipe joue
    List<Match> findByStatusAndEquipeAIdOrStatusAndEquipeBId(
            Match.Status status1, Long equipeAId,
            Match.Status status2, Long equipeBId
    );

    // 4.CS Compte les matchs terminés joués après une certaine date (pour le HealthIndicator)
    long countByStatusAndDateJoueAfter(Match.Status status, LocalDateTime date);
}
