package at.escapedoom.player.service;

import at.escapedoom.player.data.domain.SessionView;
import at.escapedoom.player.rest.model.EscapeRoomState;
import at.escapedoom.player.service.interfaces.EscapeRoomSessionRepositoryService;
import at.escapedoom.spring.communication.session.api.SessionApi;
import at.escapedoom.spring.communication.session.invoker.ApiException;
import at.escapedoom.spring.communication.session.model.EscapeRoomSessionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class SessionCommunicationService implements EscapeRoomSessionRepositoryService {

    private final SessionApi sessionApi;

    @Override
    public Optional<SessionView> getSessionInfoByRoomPin(Long roomPin) {

        try {
            return getSessionViewFromApiResponseIfAllValuesAreFilled(sessionApi.getERSessionByPin(roomPin.intValue()));
        } catch (ApiException e) {
            if (e.getCode() == 404) {
                log.debug("Room pin not found got an 404");
                return Optional.empty();
            }
            log.error("couldn't communicate with session Api via Rest when trying to Obtain Session Info by RoomPin");
            return Optional.empty();
        }
    }

    private static Optional<SessionView> getSessionViewFromApiResponseIfAllValuesAreFilled(
            EscapeRoomSessionResponse erSessionByPin) {
        if (erSessionByPin != null && erSessionByPin.getRoomPin() != null && erSessionByPin.getState() != null
                && erSessionByPin.getEscapeRoomTemplateId() != null) {
            return Optional.of(SessionView.builder().roomPin(erSessionByPin.getRoomPin().longValue())
                    .escapeRoomTemplateId(erSessionByPin.getEscapeRoomTemplateId())
                    .roomState(EscapeRoomState.fromValue(erSessionByPin.getState().toString())).build());
        }
        return Optional.empty();
    }

}
