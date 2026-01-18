package org.imt.tournamentmaster.dto.match;
import java.util.List;

import org.imt.tournamentmaster.dto.round.RoundDto;
import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.match.Match;

import jakarta.validation.constraints.NotNull;

public class MatchDto {
    private Long id;

    private Equipe equipeA;

    private Equipe equipeB;

    private List<RoundDto> rounds;

    private Match.Status status;

    public MatchDto(Long id, Equipe equipeA, Equipe equipeB, List<RoundDto> rounds, Match.Status status) {
        this.id = id;
        this.equipeA = equipeA;
        this.rounds = rounds;
        this.equipeB = equipeB;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Equipe getEquipeA() {
        return equipeA;
    }

    public void setEquipeA(Equipe equipeA) {
        this.equipeA = equipeA;
    }

    public Equipe getEquipeB() {
        return equipeB;
    }

    public void setEquipeB(Equipe equipeB) {
        this.equipeB = equipeB;
    }

    public List<RoundDto> getRounds() {
        return rounds;
    }

    public void setRounds(List<RoundDto> rounds) {
        this.rounds = rounds;
    }

    public Match.Status getStatus() {
        return status;
    }

    public void setStatus(Match.Status status) {
        this.status = status;
    }
}