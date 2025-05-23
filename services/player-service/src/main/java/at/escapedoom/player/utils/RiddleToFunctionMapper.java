package at.escapedoom.player.utils;

import at.escapedoom.spring.communication.data.model.RiddleDTO;

public class RiddleToFunctionMapper {

    public static at.escapedoom.player.rest.model.RiddleDTO riddleToFrontendFunction(
            at.escapedoom.spring.communication.data.model.RiddleDTO riddle) {

        assert riddle.getLanguage() != null;

        at.escapedoom.player.rest.model.RiddleDTO.RiddleDTOBuilder builder = at.escapedoom.player.rest.model.RiddleDTO
                .builder();

        builder.language(at.escapedoom.player.rest.model.CodingLanguage.fromValue(riddle.getLanguage().getValue()));
        builder.function(String.format("""
                import java.util.*;

                public class Main {

                    %s{

                    }
                }

                """, riddle.getFunctionSignature()));
        builder.levelId(riddle.getLevelId());
        builder.riddleId(riddle.getRiddleId());

        return builder.build();
    }

    public static String riddleToBackendFunction(String riddle, String input) {

        return riddle.replaceFirst("public class Main \\{",
                "public class Main { \n public static void main(String[] args) { \n " + input + " \n }");
    }

}
