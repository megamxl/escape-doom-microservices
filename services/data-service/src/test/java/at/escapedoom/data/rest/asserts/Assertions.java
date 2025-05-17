package at.escapedoom.data.rest.asserts;

import lombok.experimental.UtilityClass;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@UtilityClass
public class Assertions {

    public ResultMatcher[] templateCreated() {
        return new ResultMatcher[] { jsonPath("$.template_id").value("abc123"),
                jsonPath("$.name").value("My Escape Template"), jsonPath("$.description").value("Test Description") };
    }

    public ResultMatcher[] levelCreated(String templateId) {
        return new ResultMatcher[] { jsonPath("$.level_id").value("lvl001"), jsonPath("$.name").value("Classroom"),
                jsonPath("$.template_id").value(templateId), jsonPath("$.level_sequence").value(1) };
    }

    public ResultMatcher[] riddleCreated(String levelId) {
        return new ResultMatcher[] { jsonPath("$.riddle_id").value("riddle001"), jsonPath("$.level_id").value(levelId),
                jsonPath("$.function_signature").value("public static int sum(int a, int b)"),
                jsonPath("$.input").value("2,3"), jsonPath("$.variable_name").value("result"),
                jsonPath("$.expected_output").value("5") };
    }

}
