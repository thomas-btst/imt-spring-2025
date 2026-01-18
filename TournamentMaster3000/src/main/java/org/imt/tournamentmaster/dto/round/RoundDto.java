package org.imt.tournamentmaster.dto.round;

public class RoundDto {
    private long id;

    private int scoreA;

    private int scoreB;

    private int roundNumber;

    public RoundDto(long id, int scoreA, int scoreB, int roundNumber) {
        this.id = id;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
        this.roundNumber = roundNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }
}
