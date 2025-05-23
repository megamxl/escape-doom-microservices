package at.escapedoom.leaderboard.service.interfaces;

import at.escapedoom.spring.communication.session.invoker.ApiException;
import at.escapedoom.spring.communication.session.model.SessionResponse;

import java.util.List;

public interface EscapeRoomTemplateRepositoryService {
    List<SessionResponse> getSessionsByTag(String tag) throws ApiException;

    SessionResponse getSessionByRoomPin(Integer roomPin) throws ApiException;
}
