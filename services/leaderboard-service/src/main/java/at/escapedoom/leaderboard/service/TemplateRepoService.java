package at.escapedoom.leaderboard.service;

import at.escapedoom.leaderboard.service.interfaces.EscapeRoomTemplateRepositoryService;
import at.escapedoom.spring.communication.session.api.SessionApi;
import at.escapedoom.spring.communication.session.invoker.ApiException;
import at.escapedoom.spring.communication.session.model.SessionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TemplateRepoService implements EscapeRoomTemplateRepositoryService {
    private final SessionApi sessionApi;

    @Override
    public List<SessionResponse> getSessionsByTag(String tag) throws ApiException {
        return sessionApi.getERSessionByTagOrPin(tag, null);
    }

    @Override
    public SessionResponse getSessionByRoomPin(Integer roomPin) throws ApiException {
        return sessionApi.getERSessionByPin(roomPin);
    }
}
