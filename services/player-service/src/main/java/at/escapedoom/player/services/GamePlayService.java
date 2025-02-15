package at.escapedoom.player.services;

import at.escapedoom.player.entity.UserProgress;
import at.escapedoom.player.repository.UserProgressRepository;
import at.escapedoom.player.rest.model.EscapeRoomLevel;
import at.escapedoom.player.services.interfaces.EscapeRoomSessionRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GamePlayService {

    private final UserProgressRepository userProgressRepository;

    public EscapeRoomLevel getCurrentLevelByUserIdentifier(UUID userIdentifier){

        //check if user with this identifier is registered
        UserProgress user = userProgressRepository.findById(userIdentifier).orElseThrow(() -> new NoSuchElementException("Can't find user with identifier " + userIdentifier));

        //TODO get escapeRoom Session via roomPin


        // TODO if playable get the level and return the EscapeRoom Level

        return EscapeRoomLevel.builder().build();
    }

}
