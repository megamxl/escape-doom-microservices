package at.escapedoom.player.service.interfaces;

import at.escapedoom.player.rest.model.EscapeRoomSolutionSubmition;

import java.util.UUID;

public interface CodeCompilerInterface {

    public void queueCodeAttempt(UUID userIdentifier, EscapeRoomSolutionSubmition escapeRoomSolutionSubmition);

    public void updateCompileRequest();
}
