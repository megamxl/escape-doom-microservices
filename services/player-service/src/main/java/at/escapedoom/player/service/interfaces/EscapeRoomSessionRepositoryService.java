package at.escapedoom.player.service.interfaces;

import at.escapedoom.spring.redis.data.models.SessionView;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public interface EscapeRoomSessionRepositoryService {

    Optional<SessionView> getSessionInfoByRoomPin(@NotNull Long roomPin);

}
