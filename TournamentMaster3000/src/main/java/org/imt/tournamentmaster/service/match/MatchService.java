package org.imt.tournamentmaster.service.match;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import org.imt.tournamentmaster.dto.match.CreateMatchDto;
import org.imt.tournamentmaster.dto.match.MatchDto;
import org.imt.tournamentmaster.dto.match.MatchStatusDto;
import org.imt.tournamentmaster.mapper.match.MatchMapper;
import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.repository.equipe.EquipeRepository;
import org.imt.tournamentmaster.repository.equipe.JoueurRepository;
import org.imt.tournamentmaster.repository.match.MatchRepository;
import org.imt.tournamentmaster.repository.match.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper; 
    private final EquipeRepository equipeRepository;
    private final RoundRepository roundRepository;

    @Autowired
    public MatchService(MatchMapper matchMapper, MatchRepository matchRepository, EquipeRepository equipeRepository, RoundRepository roundRepository) {
        this.matchMapper = matchMapper;
        this.matchRepository = matchRepository;
        this.equipeRepository = equipeRepository;
        this.roundRepository = roundRepository;
    }

    public Match findByIdOrThrow(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match with id %d not found".formatted(id)));
    }

    @Transactional(readOnly = true)
    public MatchDto getById(long id) {
        Match match = findByIdOrThrow(id);
        return matchMapper.toMatchDto(match); 
    }

    @Transactional(readOnly = true)
    public List<MatchDto> getAll() {
        return StreamSupport.stream(matchRepository.findAll().spliterator(), false)
            .map(matchMapper::toMatchDto)
                .toList();
    }

    @Transactional
    public MatchDto createMatch(CreateMatchDto matchDto){
        Match match = matchMapper.toMatch(matchDto);

        if (Objects.equals(matchDto.getEquipeB(), matchDto.getEquipeA()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Equipe A should not be equal to equipe B");

        if (equipeRepository.findById(match.getEquipeA().getId()).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipe A doesn't exist".formatted(match.getEquipeA()));

        if (equipeRepository.findById(match.getEquipeA().getId()).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipe B doesn't exist".formatted(match.getEquipeB()));

        return matchMapper.toMatchDto(matchRepository.save(match));
    }

    @Transactional
    public MatchDto deleteMatch(Long id) {
        Match match = findByIdOrThrow(id);

        MatchDto matchDto = matchMapper.toMatchDto(match); // Create DTO before delete

        matchRepository.deleteById(id);

        return matchDto;
    }

    @Transactional
    public MatchDto updateMatchStatus(Long id, MatchStatusDto statusDto) {
        Match match = findByIdOrThrow(id);

        switch (match.getStatus()) {
            case NOUVEAU -> {
                if (statusDto.getStatus() != Match.Status.EN_COURS)
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The new status can't be applied");
            }
            case EN_COURS -> {
                if (statusDto.getStatus() != Match.Status.TERMINE)
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The new status can't be applied");
            }
            default ->
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The new status can't be applied");
        }

        match.setStatus(statusDto.getStatus());

        return matchMapper.toMatchDto(matchRepository.save(match));
    }

    // Recherche par statut uniquement
    @Transactional(readOnly = true)
    public List<Match> getByStatus(Match.Status status) {
        return matchRepository.findByStatus(status);
    }

    // Recherche les matchs où une équipe participe (en A ou en B)
    @Transactional(readOnly = true)
    public List<Match> getByEquipeId(Long equipeId) {
        return matchRepository.findByEquipeAIdOrEquipeBId(equipeId, equipeId);
    }

    // Recherche combinée : on peut filtrer par statut et/ou par équipe
    // Si un critère est null, on l'ignore
    @Transactional(readOnly = true)
    public List<MatchDto> search(Match.Status status, Long equipeId) {
        List<Match> matches;
        
        // Cas 1 : aucun critère -> tout retourner
        if (status == null && equipeId == null) {
            matches = StreamSupport.stream(matchRepository.findAll().spliterator(), false).toList();
        }
        // Cas 2 : seulement le statut
        else if (equipeId == null) {
            matches = matchRepository.findByStatus(status);
        }
        // Cas 3 : seulement l'équipe
        else if (status == null) {
            matches = matchRepository.findByEquipeAIdOrEquipeBId(equipeId, equipeId);
        }
        // Cas 4 : les deux critères
        else {
            matches = matchRepository.findByStatusAndEquipeAIdOrStatusAndEquipeBId(
                    status, equipeId, status, equipeId
            );
        }
        
        // Convertir en DTOs
        return matches.stream().map(matchMapper::toMatchDto).toList();
    }
}
