package at.escapedoom.data.mapper;

import at.escapedoom.data.data.entity.riddle.CodingRiddleEntity;
import at.escapedoom.data.data.entity.riddle.Riddle;
import at.escapedoom.data.data.entity.riddle.RiddleType;
import at.escapedoom.data.data.entity.riddle.TestCaseEntity;
import at.escapedoom.data.rest.model.*;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.SubclassMapping;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Slf4j
public class RiddleMapper {

    public static Riddle toEntity(RiddleCreationRequestDTO creationRequest) {
        log.trace("riddle was null in mapping ");
        if (creationRequest == null) {
            return null;
        }

        RiddleDTO build = RiddleDTO.builder().riddle(creationRequest.getRiddle()).levelId(creationRequest.getLevelId())
                .build();

        return toEntity(build);
    };

    public static RiddleDTO toDTO(Riddle riddle) {
        log.trace("riddle was null in mapping ");
        if (riddle == null) {
            return null;
        }

        return RiddleDTO.builder().riddleId(riddle.getRiddleId().toString()).levelId(riddle.getLevelId().toString())
                .riddle(toWrapper(riddle)).build();
    }

    public static Riddle toEntity(RiddleDTO riddle) {
        Riddle riddleEt;

        switch (riddle.getRiddle()) {
        case CodingRiddleDTO ent -> {

            HashMap<CodingLanguage, String> codeMap = new HashMap<>();

            ent.getCode().forEach((key, val) -> codeMap.put(CodingLanguage.fromValue(key), val));

            List<TestCaseEntity> testCaseEntities = new ArrayList<>();

            ent.getTestCases().forEach(testCaseEnt -> {
                testCaseEntities.add(TestCaseEntity.builder().input(testCaseEnt.getInput())
                        .expectedOutput(testCaseEnt.getExpectedOutput()).build());
            });

            return new CodingRiddleEntity(testCaseEntities, codeMap);
        }
        case null -> {
            throw new IllegalArgumentException("Riddle entity is null");
        }
        default -> throw new IllegalStateException("Unexpected value: " + riddle.getRiddle());
        }
    }

    public static RiddleWrapper toWrapper(Riddle riddle) {
        RiddleWrapper wrapper;

        switch (riddle) {
        case CodingRiddleEntity ent -> {
            HashMap<String, String> codeMap = new HashMap<>();

            ent.getCode().forEach((key, val) -> codeMap.put(key.getValue(), val));

            List<TestCaseDTO> testCaseDTOList = new ArrayList<>();

            ent.getTestCases().forEach(testCaseDTO -> {
                testCaseDTOList.add(TestCaseDTO.builder().input(testCaseDTO.getInput())
                        .expectedOutput(testCaseDTO.getExpectedOutput()).build());
            });

            wrapper = CodingRiddleDTO.builder().code(codeMap).type(RiddleType.CODING.getValue())
                    .testCases(testCaseDTOList).build();
        }

        case null, default -> throw new IllegalArgumentException(String.format("Invalid Riddle %s", riddle));
        }

        return wrapper;
    }
}
