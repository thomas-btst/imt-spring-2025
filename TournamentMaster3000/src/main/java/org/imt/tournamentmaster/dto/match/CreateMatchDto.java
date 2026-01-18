package org.imt.tournamentmaster.dto.match;
import java.util.List;

import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.model.match.Round;

import jakarta.validation.constraints.NotNull;

public class CreateMatchDto {
    @NotNull
    private Long equipeA;

    @NotNull
    private Long equipeB;

    public CreateMatchDto(Long equipeB, Long equipeA) {
        this.equipeB = equipeB;
        this.equipeA = equipeA;
    }

    public Long getEquipeA() {
        return equipeA;
    }

    public void setEquipeA(Long equipeA) {
        this.equipeA = equipeA;
    }

    public Long getEquipeB() {
        return equipeB;
    }

    public void setEquipeB(Long equipeB) {
        this.equipeB = equipeB;
    }
}