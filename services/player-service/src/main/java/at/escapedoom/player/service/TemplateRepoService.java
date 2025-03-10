package at.escapedoom.player.service;

import at.escapedoom.player.rest.model.EscapeRoomLevel;
import at.escapedoom.player.service.interfaces.EscapeRoomTemplateRepositoryService;
import at.escapedoom.spring.communication.data.api.TemplateApi;
import at.escapedoom.spring.communication.data.invoker.ApiException;
import at.escapedoom.spring.communication.data.model.TemplateDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TemplateRepoService implements EscapeRoomTemplateRepositoryService {

    private final TemplateApi templateApi;

    @Override
    public String getCompleteTemplateById(UUID templateId) throws NoSuchElementException {

        try {
            TemplateDTO template = templateApi.getTemplate(templateId.toString());

            return template.toJson();

        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EscapeRoomLevel getEscapeRoomLevelByRoomPin(Long roomPin, Long level) {
        return null;
    }
}
