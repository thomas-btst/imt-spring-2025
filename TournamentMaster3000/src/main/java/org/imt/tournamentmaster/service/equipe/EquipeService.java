package org.imt.tournamentmaster.service.equipe;

import org.imt.tournamentmaster.dto.equipe.CreateOrUpdateEquipeDto;
import org.imt.tournamentmaster.dto.equipe.EquipeDto;
import org.imt.tournamentmaster.dto.joueur.JoueurDto;
import org.imt.tournamentmaster.mapper.equipe.EquipeMapper;
import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.equipe.Joueur;
import org.imt.tournamentmaster.repository.equipe.EquipeRepository;
import org.imt.tournamentmaster.repository.equipe.JoueurRepository;
import org.imt.tournamentmaster.repository.match.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.StreamSupport;

@Service
public class EquipeService {

    private final EquipeRepository equipeRepository;
    private final EquipeMapper equipeMapper;
    private final JoueurRepository joueurRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public EquipeService(EquipeRepository equipeRepository, EquipeMapper equipeMapper, JoueurRepository joueurRepository, MatchRepository matchRepository) {
        this.equipeRepository = equipeRepository;
        this.equipeMapper = equipeMapper;
        this.joueurRepository = joueurRepository;
        this.matchRepository = matchRepository;
    }

    @Transactional
    Equipe saveEquipe(Equipe equipe) {
        List<Long> joueurIds = equipe
                .getJoueurs()
                .stream()
                .map(Joueur::getId)
                .toList();

        if (joueurRepository.countByIdIn(joueurIds) != joueurIds.size())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A joueur from the equipe does not exist");

        if (equipeRepository.existsByNomAndIdNot(equipe.getNom(), equipe.getId()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Equipe with name %s already exists".formatted(equipe.getNom()));

        if (
            equipe.getId() == null
                ? equipeRepository.existsByJoueursIn(equipe.getJoueurs())
                : equipeRepository.existsByJoueursInAndIdNot(equipe.getJoueurs(), equipe.getId())
        )
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A joueur is already affected to an equipe");

        return equipeRepository.save(equipe);
    }

    @Transactional(readOnly = true)
    public EquipeDto getById(Long id) {
        Equipe equipe = equipeRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipe with id %d not found".formatted(id)));

        return equipeMapper.toEquipeDto(equipe);
    }

    @Transactional(readOnly = true)
    public List<EquipeDto> getAll() {
        return StreamSupport.stream(equipeRepository.findAll().spliterator(), false)
            .map(equipeMapper::toEquipeDto)
            .toList();
    }

    @Transactional
    public EquipeDto createEquipe(CreateOrUpdateEquipeDto equipeDto) {
        Equipe equipe = equipeMapper.toEquipe(equipeDto);
        return equipeMapper.toEquipeDto(saveEquipe(equipe));
    }

    @Transactional
    public EquipeDto updateEquipe(Long id, CreateOrUpdateEquipeDto equipeDto) {
        if (!equipeRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipe with id %d not found".formatted(id));

        Equipe equipe = equipeMapper.toEquipe(id, equipeDto);

        return equipeMapper.toEquipeDto(saveEquipe(equipe));
    }

    @Transactional
    public EquipeDto deleteEquipe(Long id) {
        Equipe equipe = equipeRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipe with id %d not found".formatted(id)));

        if (matchRepository.existsByEquipeAOrEquipeB(equipe, equipe))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A match with the equipe %d exists".formatted(id));

        EquipeDto equipeDto = equipeMapper.toEquipeDto(equipe); // Create DTO before delete

        equipeRepository.deleteById(id);

        return equipeDto;
    }
}
