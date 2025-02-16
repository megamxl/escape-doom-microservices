package at.escapedoom.player.service;

import at.escapedoom.player.data.domain.SessionView;
import at.escapedoom.player.service.interfaces.EscapeRoomSessionRepositoryService;
import at.escapedoom.spring.communication.session.invoker.ApiClient;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SessionCommunicationService implements EscapeRoomSessionRepositoryService {

    private final OkHttpClient client;

    @Override
    public Optional<SessionView> getSessionInfoByRoomPin(Long roomPin) {

        ApiClient defaultClient = new ApiClient(client);

        defaultClient.setBasePath("http://localhost:8081/data-api/v1");

//        TemplateApi apiInstance = new TemplateApi(defaultClient);
//        EscapeRoomTemplateCreateRequest escapeRoomTemplateCreateRequest = new EscapeRoomTemplateCreateRequest();
//        escapeRoomTemplateCreateRequest.setName("test");
//        escapeRoomTemplateCreateRequest.setDescription("testDes");// List<String> | A comma-separated list of tags to filter sessions.
//        try {
//            EscapeRoomTemplateResult template = apiInstance.createTemplate(escapeRoomTemplateCreateRequest);
//            System.out.println(template);
//        } catch (ApiException e) {
//            System.err.println("Exception when calling AllLeaderboardsApi#escapeRoomSessionsGet");
//            System.err.println("Status code: " + e.getCode());
//            System.err.println("Reason: " + e.getResponseBody());
//            System.err.println("Response headers: " + e.getResponseHeaders());
//            e.printStackTrace();
//        }

        return Optional.empty();
    }
}
