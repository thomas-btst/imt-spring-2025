package org.imt.tournamentmaster.dto.equipe;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class CreateOrUpdateEquipeDto {
    @NotNull
    @NotBlank
    private String nom;

    @NotEmpty
    @Size(min = 8)
    private List<@NotNull Long> joueurs;

    public CreateOrUpdateEquipeDto(String nom, List<Long> joueurs) {
        this.nom = nom;
        this.joueurs = joueurs;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Long> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Long> joueurs) {
        this.joueurs = joueurs;
    }
}
