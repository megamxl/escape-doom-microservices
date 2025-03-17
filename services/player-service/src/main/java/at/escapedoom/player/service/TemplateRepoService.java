package at.escapedoom.player.service;

import at.escapedoom.player.rest.model.EscapeRoomLevel;
import at.escapedoom.player.service.interfaces.EscapeRoomTemplateRepositoryService;
import at.escapedoom.player.service.interfaces.LevelDtoToRestResponse;
import at.escapedoom.spring.communication.data.api.TemplateApi;
import at.escapedoom.spring.communication.data.invoker.ApiException;
import at.escapedoom.spring.communication.data.model.LevelDTO;
import at.escapedoom.spring.communication.data.model.TemplateDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TemplateRepoService implements EscapeRoomTemplateRepositoryService {

    private final TemplateApi templateApi;
    private final LevelDtoToRestResponse levelDtoToRestResponse;

    @Override
    public at.escapedoom.player.rest.model.LevelDTO getCompleteTemplateById(UUID templateId, int level)
            throws NoSuchElementException {

        try {
            TemplateDTO template = templateApi.getTemplate(templateId.toString());

            LevelDTO levelDTO = template.getLevels().get(level);

            return levelDtoToRestResponse.toRest(levelDTO);

        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EscapeRoomLevel getEscapeRoomLevelByRoomPin(Long roomPin, Long level) {
        return null;
    }
}
