package at.escapedoom.data.rest.factory;

import at.escapedoom.data.rest.model.LevelCreationRequest;
import at.escapedoom.data.rest.model.LevelDTO;

public class LevelTestFactory {

    public static LevelCreationRequest createRequest(String templateId) {
        return LevelCreationRequest.builder().name("Classroom").templateId(templateId).levelSequence(1).build();
    }

    public static LevelDTO createResponseFrom(LevelCreationRequest request) {
        return LevelDTO.builder().levelId("lvl001").name(request.getName()).templateId(request.getTemplateId())
                .levelSequence(request.getLevelSequence()).build();
    }
}
