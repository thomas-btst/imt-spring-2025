package org.imt.tournamentmaster.mapper.equipe;

import org.imt.tournamentmaster.dto.equipe.CreateOrUpdateEquipeDto;
import org.imt.tournamentmaster.dto.equipe.EquipeDto;
import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.equipe.Joueur;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EquipeMapper {
    private final JoueurMapper joueurMapper;

    public EquipeMapper(JoueurMapper joueurMapper) {
        this.joueurMapper = joueurMapper;
    }

    public Equipe toEquipe(Long id, CreateOrUpdateEquipeDto equipeDto) {
        return new Equipe(
            id,
            equipeDto.getNom(),
            equipeDto.getJoueurs().stream().map(Joueur::new).toList()
        );
    }

    public Equipe toEquipe(CreateOrUpdateEquipeDto equipeDto) {
        List<Joueur> joueurs = equipeDto.getJoueurs().stream().map(joueurId -> {
            Joueur joueur = new Joueur();
            joueur.setId(joueurId);
            return joueur;
        }).toList();

        return new Equipe(
            equipeDto.getNom(),
            joueurs
        );
    }

    public EquipeDto toEquipeDto(Equipe equipe) {
        return new EquipeDto(
            equipe.getId(),
            equipe.getNom(),
            equipe.getJoueurs().stream().map(joueurMapper::toJoueurDto).toList()
        );
    }
}
