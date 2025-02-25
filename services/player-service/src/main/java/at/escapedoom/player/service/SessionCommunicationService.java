package at.escapedoom.player.service;

import at.escapedoom.spring.redis.data.models.SessionView;
import at.escapedoom.spring.redis.data.repositories.SessionViewRepository;
import at.escapedoom.player.service.interfaces.EscapeRoomSessionRepositoryService;
import at.escapedoom.spring.communication.session.api.SessionApi;
import at.escapedoom.spring.communication.session.invoker.ApiException;
import at.escapedoom.spring.communication.session.model.EscapeRoomSessionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static at.escapedoom.player.utils.MapperFunctions.stateSessionToRedisMapper;

@Component
@RequiredArgsConstructor
@Slf4j
public class SessionCommunicationService implements EscapeRoomSessionRepositoryService {

    private final SessionApi sessionApi;
    private final SessionViewRepository sessionViewRepository;

    @Override
    public Optional<SessionView> getSessionInfoByRoomPin(Long roomPin) {

        Optional<SessionView> getSessionViewByRoomPin = sessionViewRepository.findById(roomPin);

        if (getSessionViewByRoomPin.isPresent()) {
            log.debug("Found session view with id in redis {}", roomPin);
            return getSessionViewByRoomPin;
        }

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

            return Optional.of(SessionView.builder()
                    .roomPin(erSessionByPin.getRoomPin().longValue())
                    .escapeRoomTemplateId(erSessionByPin.getEscapeRoomTemplateId())
                    .roomState(stateSessionToRedisMapper(erSessionByPin.getState()))
                    .build());
        }
        return Optional.empty();
    }
}
