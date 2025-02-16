package at.escapedoom.player.service;

import at.escapedoom.player.data.domain.SessionView;
import at.escapedoom.player.rest.model.EscapeRoomState;
import at.escapedoom.player.service.interfaces.EscapeRoomSessionRepositoryService;
import at.escapedoom.player.utils.AuthorizationHeaderIntercept;
import at.escapedoom.spring.communication.data.api.*;
import at.escapedoom.spring.communication.data.invoker.*;
import at.escapedoom.spring.communication.data.model.*;
import jakarta.validation.constraints.NotNull;
import okhttp3.OkHttpClient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class MOCKEscapeRoomSessionRepositoryImpl implements EscapeRoomSessionRepositoryService {
    @Override
    public Optional<SessionView> getSessionInfoByRoomPin(@NotNull Long roomPin) {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthorizationHeaderIntercept())
                .build();

        ApiClient defaultClient = new ApiClient(client);
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        defaultClient.addDefaultHeader("Authorization", "Bearer " + authentication.getToken().getTokenValue());
        defaultClient.setBasePath("http://localhost:8081/data-api/v1");

        TemplateApi apiInstance = new TemplateApi(defaultClient);
        EscapeRoomTemplateCreateRequest escapeRoomTemplateCreateRequest = new EscapeRoomTemplateCreateRequest();
        escapeRoomTemplateCreateRequest.setName("test");
        escapeRoomTemplateCreateRequest.setDescription("testDes");// List<String> | A comma-separated list of tags to filter sessions.
        try {
            EscapeRoomTemplateResult template = apiInstance.createTemplate(escapeRoomTemplateCreateRequest);
            System.out.println(template);
        } catch (ApiException e) {
            System.err.println("Exception when calling AllLeaderboardsApi#escapeRoomSessionsGet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }

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
