package at.escapedoom.player.utils;

import at.escapedoom.spring.communication.data.model.RiddleDTO;

public class RiddleToFunctionMapper {

    public static at.escapedoom.player.rest.model.RiddleDTO riddleToFrontendFunction(
            at.escapedoom.spring.communication.data.model.RiddleDTO riddle) {

        assert riddle.getLanguage() != null;

        at.escapedoom.player.rest.model.RiddleDTO.RiddleDTOBuilder builder = at.escapedoom.player.rest.model.RiddleDTO
                .builder();

        builder.language(at.escapedoom.player.rest.model.CodingLanguage.fromValue(riddle.getLanguage().getValue()));
        builder.function(riddle.getFunctionSignature() + "{ \n \n \n}");
        builder.levelId(riddle.getLevelId());
        builder.riddleId(riddle.getRiddleId());
        builder.expectedOutput(riddle.getExpectedOutput());

        return builder.build();
    }

    public static String riddleToBackendFunction(RiddleDTO riddle) {
        return null;
    }

}
