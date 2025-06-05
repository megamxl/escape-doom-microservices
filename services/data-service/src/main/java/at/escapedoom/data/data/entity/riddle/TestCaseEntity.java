package at.escapedoom.data.data.entity.riddle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestCaseEntity {
    private Map<String, String> input = new HashMap<>();
    private Map<String, String> expectedOutput = new HashMap<>();
}
