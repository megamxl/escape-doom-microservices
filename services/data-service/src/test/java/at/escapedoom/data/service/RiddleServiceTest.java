package at.escapedoom.data.service;

import at.escapedoom.data.DataApi;
import at.escapedoom.data.data.RiddleRepository;
import at.escapedoom.data.data.entity.Riddle;
import at.escapedoom.data.rest.model.CodingLanguage;
import at.escapedoom.data.rest.model.RiddleCreationRequestDTO;
import at.escapedoom.data.rest.model.RiddleDTO;
import at.escapedoom.data.rest.model.RiddleDeletionResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DataApi.class)
@ActiveProfiles("test")
class RiddleServiceTest {

    private String VALID_RIDDLE_ID = "";
    private final String INVALID_RIDDLE_ID = "05c48cb1-a3aa-4673-8d24-666666666666";

    @Autowired
    private RiddleService service;
    @Autowired
    private RiddleRepository repository;

    @Transactional
    @BeforeEach
    void setup() {

        repository.deleteAllInBatch();
        repository.flush(); // Ensures delete operation is executed immediately

        Riddle riddle = Riddle.builder().input("2, 3").language(CodingLanguage.JAVA).expectedOutput("5")
                .functionSignature("public static int sum(int a, int b)").variableName("result").build();

        VALID_RIDDLE_ID = repository.save(riddle).getRiddleId().toString();

    }

    // region GET Tests
    @Test
    void testGetAllRiddles() {
        List<RiddleDTO> riddles = service.getAllRiddles();
        assertEquals(1, riddles.size());
        assertEquals(CodingLanguage.JAVA, riddles.get(0).getLanguage());
    }

    @Test
    void testGetRiddleById() {
        RiddleDTO riddle = service.getRiddleById(VALID_RIDDLE_ID);

        assertEquals(CodingLanguage.JAVA, riddle.getLanguage());
        assertEquals("2, 3", riddle.getInput());
        assertEquals("5", riddle.getExpectedOutput());
    }

    @Test
    void testGetRiddleByIdError() {
        assertThrows(IllegalArgumentException.class, () -> service.getRiddleById(UUID.randomUUID().toString()));
    }
    // endregion

    // region DELETE Tests
    @Test
    void testDeleteRiddle() {
        RiddleDeletionResponseDTO response = service.deleteRiddle(VALID_RIDDLE_ID);
        assertEquals("Riddle deleted successfully", response.getMessage());
    }

    @Test
    void testDeleteRiddleNotFoundError() {
        assertThrows(NoSuchElementException.class, () -> service.deleteRiddle(INVALID_RIDDLE_ID));
    }

    @Test
    void testDeleteRiddleNullError() {
        assertThrows(AssertionError.class, () -> service.deleteRiddle(null));
    }
    // endregion

    // region PUT Tests
    @Test
    void testUpdateRiddle() {
        RiddleDTO riddle = service.getRiddleById(VALID_RIDDLE_ID);

        final String newOutput = "420";
        final String newVariableName = "blazeIt";

        RiddleCreationRequestDTO riddleCreationRequest = RiddleCreationRequestDTO.builder().expectedOutput(newOutput)
                .variableName(newVariableName).build();

        RiddleDTO updatedRiddle = service.updateRiddle(riddleCreationRequest, riddle.getRiddleId());

        assertEquals(riddle.getRiddleId(), updatedRiddle.getRiddleId());
        assertEquals(newOutput, updatedRiddle.getExpectedOutput());
    }

    @Test
    void testUpdateRiddleNullError() {
        assertThrows(AssertionError.class, () -> service.updateRiddle(null, null));
    }

    @Test
    void testUpdateRiddleNotFoundError() {

        RiddleCreationRequestDTO riddleCreationRequest = RiddleCreationRequestDTO.builder()
                .expectedOutput("ILikeCheese").build();

        assertThrows(IllegalArgumentException.class,
                () -> service.updateRiddle(riddleCreationRequest, INVALID_RIDDLE_ID));
    }

    @Test
    void testUpdateRiddleEmptyRequest() {

        RiddleDTO riddle = service.getRiddleById(VALID_RIDDLE_ID);

        RiddleCreationRequestDTO riddleCreationRequest = RiddleCreationRequestDTO.builder().build();

        RiddleDTO updatedRiddle = service.updateRiddle(riddleCreationRequest, VALID_RIDDLE_ID);

        assertEquals(riddle.getRiddleId(), updatedRiddle.getRiddleId());
        assertEquals(riddle.getInput(), updatedRiddle.getInput());
    }
    // endregion

    // region POST Tests
    @Test
    void testCreateRiddle() {
        final String EXPECTED_OUTPUT = "666";
        final String EXPECTED_INPUT = "660, 6";
        final CodingLanguage EXPECTED_LANGUAGE = CodingLanguage.JAVA;
        final String EXPECTED_VARIABLE = "devil";
        final String EXPECTED_FUNCTION = "public static int sum(int a, int b)";

        RiddleCreationRequestDTO riddleCreationRequest = RiddleCreationRequestDTO.builder().language(EXPECTED_LANGUAGE)
                .expectedOutput(EXPECTED_OUTPUT).input(EXPECTED_INPUT).functionSignature(EXPECTED_FUNCTION)
                .variableName(EXPECTED_VARIABLE).build();

        RiddleDTO riddle = service.createRiddle(riddleCreationRequest);

        assertEquals(EXPECTED_INPUT, riddle.getInput());
        assertEquals(EXPECTED_OUTPUT, riddle.getExpectedOutput());
        assertEquals(EXPECTED_FUNCTION, riddle.getFunctionSignature());
        assertEquals(EXPECTED_LANGUAGE, riddle.getLanguage());
    }

    @Test
    void testCreateRiddleNullError() {
        assertThrows(AssertionError.class, () -> service.createRiddle(null));
    }

    @Test
    void testCreateRiddleParameterMissing() {
        RiddleCreationRequestDTO riddleCreationRequest = RiddleCreationRequestDTO.builder()
                .language(CodingLanguage.JAVA).build();

        assertEquals(CodingLanguage.JAVA, riddleCreationRequest.getLanguage());
        assertNull(riddleCreationRequest.getFunctionSignature());
    }
    // endregion
}
