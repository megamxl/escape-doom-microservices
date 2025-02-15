package at.escapedoom.player.services.interfaces;

import at.escapedoom.player.domain.SessionView;
import jakarta.validation.constraints.NotNull;

import java.util.NoSuchElementException;
import java.util.Optional;

public interface EscapeRoomSessionRepositoryService {

    Optional<SessionView> getSessionInfoByRoomPin(@NotNull Long roomPin);

}
