package at.escapedoom.player.mock;

import at.escapedoom.player.data.domain.SessionView;
import at.escapedoom.player.rest.model.EscapeRoomState;
import at.escapedoom.player.service.interfaces.EscapeRoomSessionRepositoryService;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class MOCKEscapeRoomSessionRepositoryImpl implements EscapeRoomSessionRepositoryService {

    @Override
    public Optional<SessionView> getSessionInfoByRoomPin(@NotNull Long roomPin) {

        if (roomPin < 200000) {
            return Optional.of(SessionView.builder().escapeRoomTemplateId(UUID.randomUUID())
                    .roomState(EscapeRoomState.CLOSED).build());
        } else if (roomPin > 200000 && roomPin < 500001) {
            return Optional.of(SessionView.builder().escapeRoomTemplateId(UUID.randomUUID())
                    .roomState(EscapeRoomState.STARTED).build());
        } else {
            return Optional.empty();
        }
    }
}
