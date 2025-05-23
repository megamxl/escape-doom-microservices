package at.escapedoom.leaderboard.service;

import at.escapedoom.leaderboard.data.ResultRepository;
import at.escapedoom.leaderboard.data.UserProgressRepository;
import at.escapedoom.leaderboard.rest.model.EscapeRoomSessionResponse;
import at.escapedoom.leaderboard.rest.model.FullExportResponse;
import at.escapedoom.leaderboard.rest.model.Result;
import at.escapedoom.leaderboard.rest.model.UserProgress;
import at.escapedoom.spring.communication.session.invoker.ApiException;
import at.escapedoom.spring.communication.session.model.SessionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EscapeRoomLeaderBoardService {

    private final ResultRepository resultRepository;
    private final UserProgressRepository userProgressRepository;
    private final TemplateRepoService templateRepoService;

    public List<FullExportResponse> getLeaderBoardsOfSessionsWithTag(String tag) {
        List<SessionResponse> sessions = new ArrayList<>();
        try {
            sessions.addAll(templateRepoService.getSessionsByTag(tag));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<FullExportResponse> resp = new ArrayList<>();
        sessions.forEach(s -> {
            resp.add(buildResp(s));
        });

        return resp;
    }

    private FullExportResponse buildResp(SessionResponse session) {
        return FullExportResponse.builder()
                .session(convertToEscapeRoomSessionResponse(session))
                .userProgress(getUserProgessesBySession(session))
                .build();
    }

    public List<UserProgress> getLeaderBoardByRoomPin(Integer roomPin) {

        SessionResponse session = null;
        try {
            session = templateRepoService.getSessionByRoomPin(roomPin);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
        return getUserProgessesBySession(session);
    }

    public List<FullExportResponse> getFullLeaderBoardByRoomPin(Integer roomPin) {
        SessionResponse session = null;
        try {
            session = templateRepoService.getSessionByRoomPin(roomPin);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
        List<FullExportResponse> resp = new ArrayList<>();
        resp.add(buildResp(session));
        return resp;
    }

    public EscapeRoomSessionResponse convertToEscapeRoomSessionResponse(SessionResponse session) {
        return EscapeRoomSessionResponse.builder()
                .state(EscapeRoomSessionResponse.StateEnum.fromValue(session.getState().name().toUpperCase()))
                .tags(session.getTags())
                .templateId(session.getTemplateId())
                .playTime(session.getPlayTime())
                .roomPin(session.getRoomPin())
                .sessionId(session.getSessionId())
                .build();
    }

    public List<UserProgress> getUserProgessesBySession( SessionResponse session){
        return userProgressRepository.getUserProgressesByRoomPin(session.getRoomPin().longValue()).stream().map(e -> UserProgress
                .builder().sessionId(session.getSessionId())
                .playerName(e.getUserName()).currentEscapeRoomLevel(e.getCurrentEscapeRoomLevel().intValue())
                .score(e.getCurrentPoints().intValue())
                .lastRiddleSolvedAt(
                        e.getLastRiddleSolvedAt() != null ? e.getLastRiddleSolvedAt().atOffset(ZoneOffset.UTC) : null)
                .results(resultRepository.getResultsByUserProgress_UserIdentifier(e.getUserIdentifier())
                        .orElse(Collections.emptyList()).stream()
                        .map(result -> Result.builder().input(result.getInput())
                                .addedPoints(result.getAwardedPoints().intValue())
                                .currentEscapeRoomLevel(result.getEscapeRoomLevel().intValue())
                                .solvedDate(result.getSolvedLevelAt() != null
                                        ? result.getSolvedLevelAt().atOffset(ZoneOffset.UTC) : null)
                                .build())
                        .toList())
                .build()).toList();
    }


}
