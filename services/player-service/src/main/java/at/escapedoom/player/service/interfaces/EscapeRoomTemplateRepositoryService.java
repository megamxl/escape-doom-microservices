package at.escapedoom.player.service.interfaces;

import at.escapedoom.player.rest.model.EscapeRoomLevel;
import at.escapedoom.player.rest.model.LevelDTO;

import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.logging.Level;

public interface EscapeRoomTemplateRepositoryService {

    LevelDTO getCompleteTemplateById(UUID templateId, int level) throws NoSuchElementException;

    EscapeRoomLevel getEscapeRoomLevelByRoomPin(Long roomPin, Long level);

}
