package org.imt.tournamentmaster.repository.equipe;

import org.imt.tournamentmaster.model.equipe.Joueur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface JoueurRepository extends CrudRepository<Joueur, Long> {
    long countByIdIn(Collection<Long> ids);
}
