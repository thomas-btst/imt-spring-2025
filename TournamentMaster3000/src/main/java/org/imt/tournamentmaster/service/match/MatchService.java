package org.imt.tournamentmaster.service.match;

import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.repository.match.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Match> getById(long id) {
        return matchRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Match> getAll() {
        return StreamSupport.stream(matchRepository.findAll().spliterator(), false)
                .toList();
    }

    // Recherche par statut uniquement
    @Transactional(readOnly = true)
    public List<Match> getByStatus(Match.Status status) {
        return matchRepository.findByStatus(status);
    }

    // Recherche les matchs où une équipe participe (en A ou en B)
    @Transactional(readOnly = true)
    public List<Match> getByEquipeId(Long equipeId) {
        return matchRepository.findByEquipeAIdOrEquipeBId(equipeId, equipeId);
    }

    // Recherche combinée : on peut filtrer par statut et/ou par équipe
    // Si un critère est null, on l'ignore
    @Transactional(readOnly = true)
    public List<Match> search(Match.Status status, Long equipeId) {
        // Cas 1 : aucun critère -> tout retourner
        if (status == null && equipeId == null) {
            return getAll();
        }

        // Cas 2 : seulement le statut
        if (equipeId == null) {
            return matchRepository.findByStatus(status);
        }

        // Cas 3 : seulement l'équipe
        if (status == null) {
            return matchRepository.findByEquipeAIdOrEquipeBId(equipeId, equipeId);
        }

        // Cas 4 : les deux critères
        return matchRepository.findByStatusAndEquipeAIdOrStatusAndEquipeBId(
                status, equipeId, status, equipeId
        );
    }
}
