package at.escapedoom.data.service.rest.factory;

import at.escapedoom.data.rest.model.RiddleCreationRequestDTO;
import at.escapedoom.data.rest.model.RiddleDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RiddleTestFactory {

    public RiddleCreationRequestDTO createRequest(String levelId) {
        return RiddleCreationRequestDTO.builder().levelId(levelId)
                .functionSignature("public static int sum(int a, int b)").input("2,3").variableName("result")
                .expectedOutput("5").build();
    }

    public RiddleDTO createResponseFrom(RiddleCreationRequestDTO request) {
        return RiddleDTO.builder().riddleId("riddle001") // <== hardcoded for consistency
                .levelId(request.getLevelId()).functionSignature(request.getFunctionSignature())
                .input(request.getInput()).variableName(request.getVariableName())
                .expectedOutput(request.getExpectedOutput()).build();
    }
}
