package at.escapedoom.player.service;

import at.escapedoom.player.data.postgres.entity.UserProgress;
import at.escapedoom.player.data.postgres.repository.UserProgressRepository;
import at.escapedoom.player.rest.model.EscapeRoomLevel;
import at.escapedoom.player.service.interfaces.EscapeRoomTemplateRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GamePlayService {

    private final UserProgressRepository userProgressRepository;

     private final EscapeRoomTemplateRepositoryService escapeRoomTemplateRepositoryService;

    public EscapeRoomLevel getCurrentLevelByUserIdentifier(UUID userIdentifier) {

        // check if user with this identifier is registered
        UserProgress user = userProgressRepository.findById(userIdentifier)
                .orElseThrow(() -> new NoSuchElementException("Can't find user with identifier " + userIdentifier));

         String completeTemplateById =
        escapeRoomTemplateRepositoryService.getCompleteTemplateById(user.getTemplateID());
        //
        //
         log.info("completeTemplateById : {}", completeTemplateById);

        // TODO get escapeRoom Session via roomPin

        // TODO if playable get the level and return the EscapeRoom Level

        return EscapeRoomLevel.builder().build();
    }

}
