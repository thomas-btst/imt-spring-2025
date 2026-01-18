package org.imt.tournamentmaster.service.match;

import org.imt.tournamentmaster.dto.round.CreateRoundDto;
import org.imt.tournamentmaster.dto.round.RoundDto;
import org.imt.tournamentmaster.mapper.match.MatchMapper;
import org.imt.tournamentmaster.mapper.match.RoundMapper;
import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.repository.equipe.EquipeRepository;
import org.imt.tournamentmaster.repository.match.MatchRepository;
import org.imt.tournamentmaster.repository.match.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class RoundService {

    private final RoundRepository roundRepository;

    private final MatchRepository matchRepository;

    private final RoundMapper roundMapper;

    @Autowired
    public RoundService(RoundRepository roundRepository, MatchRepository matchRepository, RoundMapper roundMapper) {
        this.roundRepository = roundRepository;
        this.matchRepository = matchRepository;
        this.roundMapper = roundMapper;
    }

    @Transactional(readOnly = true)
    public RoundDto getById(long id) {
        Round round = roundRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Round with id %d does not exist".formatted(id)));
        return roundMapper.toRoundDto(round);
    }

    @Transactional(readOnly = true)
    public List<RoundDto> getAll() {
        return StreamSupport.stream(roundRepository.findAll().spliterator(), false)
            .map(roundMapper::toRoundDto)
            .toList();
    }

    @Transactional(readOnly = true)
    public List<RoundDto> getByScoreAGreaterThanEqual(int scoreA) {
        return roundRepository
            .findByScoreAGreaterThanEqual(scoreA)
            .stream()
            .map(roundMapper::toRoundDto)
            .toList();
    }

    @Transactional
    public RoundDto createRound(CreateRoundDto roundDto) {
        Match match = matchRepository
                .findById(roundDto.getMatchId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match with id %d does not exists".formatted(roundDto.getMatchId())));

        Round round = roundMapper.toRound(roundDto);
        round.setRoundNumber(match.getRounds().size() + 1);
        Round updatedRound = roundRepository.save(round);

        match.getRounds().add(updatedRound);
        matchRepository.save(match);

        return roundMapper.toRoundDto(updatedRound);
    }

}
