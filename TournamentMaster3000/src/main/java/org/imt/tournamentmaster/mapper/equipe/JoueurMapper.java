package org.imt.tournamentmaster.mapper.equipe;

import org.imt.tournamentmaster.dto.joueur.CreateOrUpdateJoueurDto;
import org.imt.tournamentmaster.dto.joueur.JoueurDto;
import org.imt.tournamentmaster.model.equipe.Joueur;
import org.springframework.stereotype.Component;

@Component
public class JoueurMapper {
    public Joueur toJoueur(CreateOrUpdateJoueurDto joueurDto) {
        return new Joueur(
            joueurDto.getNom(),
            joueurDto.getPrenom(),
            joueurDto.getNumero()
        );
    }

    public Joueur toJoueur(long id, CreateOrUpdateJoueurDto joueurDto) {
        Joueur joueur = toJoueur(joueurDto);
        joueur.setId(id);
        return joueur;
    }

    public JoueurDto toJoueurDto(Joueur joueur) {
        return new JoueurDto(
            joueur.getId(),
            joueur.getNom(),
            joueur.getPrenom(),
            joueur.getNumero()
        );
    }
}
