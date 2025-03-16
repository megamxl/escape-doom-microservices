package at.escapedoom.player.service.interfaces;

import at.escapedoom.player.rest.model.EscapeRoomLevel;

import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.logging.Level;

public interface EscapeRoomTemplateRepositoryService {

    EscapeRoomLevel getCompleteTemplateById(UUID templateId, int level) throws NoSuchElementException;

    EscapeRoomLevel getEscapeRoomLevelByRoomPin(Long roomPin, Long level);

}
