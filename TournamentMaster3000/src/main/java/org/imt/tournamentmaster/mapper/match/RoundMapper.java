package org.imt.tournamentmaster.mapper.match;

import org.imt.tournamentmaster.dto.round.CreateRoundDto;
import org.imt.tournamentmaster.dto.round.RoundDto;
import org.imt.tournamentmaster.model.match.Round;
import org.springframework.stereotype.Component;

@Component
public class RoundMapper {
    public Round toRound(CreateRoundDto roundDto) {
        Round round = new Round();
        round.setScoreA(roundDto.getScoreA());
        round.setScoreB(roundDto.getScoreB());
        return round;
    }

    public RoundDto toRoundDto(Round round) {
        return new RoundDto(
            round.getId(),
            round.getScoreA(),
            round.getScoreB(),
            round.getRoundNumber()
        );
    }
}
