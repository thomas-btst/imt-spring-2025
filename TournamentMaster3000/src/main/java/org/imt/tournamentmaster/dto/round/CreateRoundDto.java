package org.imt.tournamentmaster.dto.round;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreateRoundDto {
    @NotNull
    private long matchId;

    @NotNull
    @Min(0)
    private int scoreA;

    @NotNull
    @Min(0)
    private int scoreB;

    public CreateRoundDto(long matchId, int scoreA, int scoreB) {
        this.matchId = matchId;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public int getScoreA() {
        return scoreA;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }
}
