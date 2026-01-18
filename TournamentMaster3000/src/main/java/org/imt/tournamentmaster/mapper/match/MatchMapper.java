package org.imt.tournamentmaster.mapper.match;

import java.util.ArrayList;

import org.imt.tournamentmaster.dto.match.CreateMatchDto;
import org.imt.tournamentmaster.dto.match.MatchDto;
import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.match.Match;
import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.stereotype.Component;

@Component
public class MatchMapper {
    private final RoundMapper roundMapper;

    public MatchMapper(RoundMapper roundMapper) {
        this.roundMapper = roundMapper;
    }

    public Match toMatch (CreateMatchDto matchDto){
        Equipe equipeA = new Equipe();
        equipeA.setId(matchDto.getEquipeA());

        Equipe equipeB = new Equipe();
        equipeB.setId(matchDto.getEquipeB());

        Match match = new Match(
            equipeA,
            equipeB
        );

        match.setRounds(new ArrayList<>());
        match.setStatus(Match.Status.NOUVEAU);
        return match;
    }

    // public Match toMatch (Long id, CreateOrUpdateMatchDto matchDto){
    //     Match match = toMatch(matchDto);
    //     match.setId(id);
    //     return match; 
    // }

    public MatchDto toMatchDto (Match match){
        return new MatchDto(
            match.getId(),
            match.getEquipeA(), 
            match.getEquipeB(),
            match.getRounds().stream().map(roundMapper::toRoundDto).toList(),
            match.getStatus()
        );
    }
}
