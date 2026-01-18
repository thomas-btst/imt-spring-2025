package org.imt.tournamentmaster.dto.match;

import jakarta.validation.constraints.NotNull;
import org.imt.tournamentmaster.model.match.Match;

public class MatchStatusDto {
    @NotNull
    Match.Status status;

    public MatchStatusDto(Match.Status status) {
        this.status = status;
    }

    public Match.Status getStatus() {
        return status;
    }

    public void setStatus(Match.Status status) {
        this.status = status;
    }
}
