package at.escapedoom.player.services.interfaces;

import at.escapedoom.player.rest.model.EscapeRoomLevel;

import java.util.NoSuchElementException;
import java.util.UUID;

public interface EscapeRoomTemplateRepositoryService {

    String getCompleteTemplateById(UUID templateId) throws NoSuchElementException;

    EscapeRoomLevel getEscapeRoomLevelByRoomPin(Long roomPin, Long level);

}
