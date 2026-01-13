package org.imt.tournamentmaster.repository.equipe;

import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.equipe.Joueur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EquipeRepository extends CrudRepository<Equipe, Long> {
    boolean existsByNomAndIdNot(String nom, Long id);

    boolean existsByJoueursIn(Collection<Joueur> joueurs);

    boolean existsByJoueursInAndIdNot(Collection<Joueur> joueurs, long id);

    boolean existsByJoueursContains(Joueur joueur);
}
