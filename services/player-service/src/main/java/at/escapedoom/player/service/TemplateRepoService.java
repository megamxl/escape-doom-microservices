package at.escapedoom.player.service;

import at.escapedoom.player.rest.model.EscapeRoomLevel;
import at.escapedoom.player.service.interfaces.EscapeRoomTemplateRepositoryService;
import at.escapedoom.player.service.interfaces.LevelDtoToRestResponse;
import at.escapedoom.player.utils.RiddleToFunctionMapper;
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

            at.escapedoom.player.rest.model.LevelDTO rest = levelDtoToRestResponse.toRest(levelDTO);

            if (levelDTO.getRiddle() == null) {
                throw new NoSuchElementException("No Riddle found for level talk with you teacher " + level);
            }

            rest.setRiddle(RiddleToFunctionMapper.riddleToFrontendFunction(levelDTO.getRiddle()));
            return rest;

        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LevelDTO getFullCurrentLevelByUserIdentifier(UUID templateId, int level) throws NoSuchElementException {

        try {
            TemplateDTO template = templateApi.getTemplate(templateId.toString());

            LevelDTO levelDTO = template.getLevels().get(level);

            if (levelDTO.getRiddle() == null) {
                throw new NoSuchElementException("No Riddle found for level talk with you teacher " + level);
            }
            return levelDTO;
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EscapeRoomLevel getEscapeRoomLevelByRoomPin(Long roomPin, Long level) {
        return null;
    }

    @Override
    public int getNumberOfLevels(UUID templateId) {
        int size = 0;
        try {
            TemplateDTO template = templateApi.getTemplate(templateId.toString());
            size = template.getLevels().size();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
        return size;
    }
}
