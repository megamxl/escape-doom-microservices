package at.escapedoom.player.service;

import at.escapedoom.player.data.code_exec.PistonRequest;
import at.escapedoom.player.data.code_exec.PistonResponse;
import at.escapedoom.player.data.postgres.entity.SolutionAttempt;
import at.escapedoom.player.data.postgres.entity.UserProgress;
import at.escapedoom.player.data.postgres.repository.SolutionAttemptRepository;
import at.escapedoom.player.data.postgres.repository.UserProgressRepository;
import at.escapedoom.player.rest.model.EscapeRoomResult;
import at.escapedoom.player.rest.model.EscapeRoomSolutionSubmition;
import at.escapedoom.player.service.interfaces.CodeCompilerInterface;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
public class CodeCompilerApiInterfaceImpl implements CodeCompilerInterface {

    private final SolutionAttemptRepository solutionAttemptRepository;
    private final UserProgressRepository userProgressRepository;
    private List<SolutionAttempt> attemptList = new ArrayList<>();

    private static final String API_URL = "https://emkc.org/api/v2/piston/execute";
    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();

    @Override
    public void queueCodeAttempt(UUID userIdentifier, EscapeRoomSolutionSubmition escapeRoomSolutionSubmition) {

        UserProgress user = userProgressRepository.findById(userIdentifier)
                .orElseThrow(() -> new NoSuchElementException("Can't find user with identifier " + userIdentifier));

        SolutionAttempt solutionAttempt = SolutionAttempt.builder().playerUUID(userIdentifier)
                .language(escapeRoomSolutionSubmition.getLanguage())
                .currentEscapeRoomLevel(user.getCurrentEscapeRoomLevel()).status(EscapeRoomResult.StatusEnum.WAITING)
                .codeSubmition(escapeRoomSolutionSubmition.getSolution()).build();

        attemptList.add(solutionAttemptRepository.save(solutionAttempt));

    }

    @Override
    @Scheduled(fixedDelay = 5000)
    public void updateCompileRequest() {
        Iterator<SolutionAttempt> iterator = attemptList.iterator();
        while (iterator.hasNext()) {
            SolutionAttempt attempt = iterator.next();
            if (attempt.getStatus() == EscapeRoomResult.StatusEnum.WAITING) {
                sendSolutionToCodeExec(attempt);
            } else {
                iterator.remove();
            }
        }
    }

    private void sendSolutionToCodeExec(SolutionAttempt attempt) {
        // TODO currently only java hardcoded somehow we need to map
        // SolutionAttempt.LanguageEnum to language tags supported by the api
        // can be queried from GET https://emkc.org/api/v2/piston/runtimes
        PistonRequest pistonRequest = PistonRequest.builder().language("java").version("15.0.2")
                .files(List.of(
                        PistonRequest.CodeFile.builder().name("Main.java").content(attempt.getCodeSubmition()).build()))
                .build();

        RequestBody body = RequestBody.create(gson.toJson(pistonRequest), MediaType.get("application/json"));

        Request request = new Request.Builder().url(API_URL).post(body).build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful())
                throw new IOException("Call failed" + response);

            assert response.body() != null;
            String responseBody = response.body().string();
            PistonResponse result = gson.fromJson(responseBody, PistonResponse.class);

            if (result.getRun().getCode() == 0) {
                attempt.setStatus(EscapeRoomResult.StatusEnum.COMPILED);
                attempt.setOutput(result.getRun().getOutput());
            } else {
                attempt.setStatus(EscapeRoomResult.StatusEnum.ERROR);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        solutionAttemptRepository.save(attempt);
    }
}
