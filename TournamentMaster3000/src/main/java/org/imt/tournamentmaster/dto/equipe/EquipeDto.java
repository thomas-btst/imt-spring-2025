package org.imt.tournamentmaster.dto.equipe;

import org.imt.tournamentmaster.dto.joueur.JoueurDto;

import java.util.List;

public class EquipeDto {
    private Long id;

    private String nom;

    private List<JoueurDto> joueurs;

    public EquipeDto() {}

    public EquipeDto(Long id, String nom, List<JoueurDto> joueurs) {
        this.id = id;
        this.nom = nom;
        this.joueurs = joueurs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<JoueurDto> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<JoueurDto> joueurs) {
        this.joueurs = joueurs;
    }
}