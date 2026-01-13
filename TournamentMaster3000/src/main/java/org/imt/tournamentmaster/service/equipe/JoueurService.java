package org.imt.tournamentmaster.service.equipe;

import org.imt.tournamentmaster.dto.joueur.CreateOrUpdateJoueurDto;
import org.imt.tournamentmaster.dto.joueur.JoueurDto;
import org.imt.tournamentmaster.mapper.equipe.JoueurMapper;
import org.imt.tournamentmaster.model.equipe.Joueur;
import org.imt.tournamentmaster.repository.equipe.EquipeRepository;
import org.imt.tournamentmaster.repository.equipe.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class JoueurService {

    private final JoueurRepository joueurRepository;
    private final JoueurMapper joueurMapper;
    private final EquipeRepository equipeRepository;

    @Autowired
    public JoueurService(JoueurRepository joueurRepository, JoueurMapper joueurMapper, EquipeRepository equipeRepository) {
        this.joueurRepository = joueurRepository;
        this.joueurMapper = joueurMapper;
        this.equipeRepository = equipeRepository;
    }

    @Transactional(readOnly = true)
    public JoueurDto getById(Long id) {
        Joueur joueur = joueurRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur with id %d not found".formatted(id)));
        return joueurMapper.toJoueurDto(joueur);
    }

    @Transactional(readOnly = true)
    public List<JoueurDto> getAll() {
        return StreamSupport
                .stream(joueurRepository.findAll().spliterator(), false)
                .map(joueurMapper::toJoueurDto)
                .toList();
    }

    @Transactional
    public JoueurDto createJoueur(CreateOrUpdateJoueurDto joueurDto) {
        Joueur joueur = joueurMapper.toJoueur(joueurDto);
        joueurRepository.save(joueur);
        return joueurMapper.toJoueurDto(joueur);
    }

    @Transactional
    public JoueurDto updateJoueur(Long id, CreateOrUpdateJoueurDto joueurDto) {
        if (!joueurRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur with id %d not found".formatted(id));
        Joueur joueur = joueurMapper.toJoueur(id, joueurDto);
        Joueur updatedJoueur = joueurRepository.save(joueur);
        return joueurMapper.toJoueurDto(updatedJoueur);
    }

    @Transactional
    public JoueurDto deleteJoueur(Long id) {
        Joueur joueur = joueurRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur with id %d not found".formatted(id)));

        if (equipeRepository.existsByJoueursContains(joueur))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Joueur is assigned to an equipe");

        JoueurDto joueurDto = joueurMapper.toJoueurDto(joueur); // Create DTO before delete

        joueurRepository.delete(joueur);

        return joueurDto;
    }
}
