package at.escapedoom.player.utils;

import at.escapedoom.player.rest.model.CodingLanguage;
import at.escapedoom.player.rest.model.CodingRiddleDTO;
import at.escapedoom.player.rest.model.EscapeRoomSolutionSubmition;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.apache.commons.lang3.NotImplementedException;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static at.escapedoom.player.rest.model.CodingLanguage.JAVA;

public class RiddleToFunctionMapper {

    public static at.escapedoom.player.rest.model.RiddleDTO riddleToFrontendFunction(
            at.escapedoom.spring.communication.data.model.RiddleDTO riddle) {

        assert riddle != null;

        at.escapedoom.player.rest.model.RiddleDTO.RiddleDTOBuilder builder = at.escapedoom.player.rest.model.RiddleDTO
                .builder();

        switch (Objects.requireNonNull(riddle.getRiddle()).getActualInstance()) {
        case at.escapedoom.spring.communication.data.model.CodingRiddleDTO coding -> {

            builder.levelId(riddle.getLevelId());
            builder.riddleId(riddle.getRiddleId());

            removeLectorCode(coding.getCode());

            CodingRiddleDTO frontendRiddleMap = CodingRiddleDTO.builder().code(coding.getCode()).type(coding.getType())
                    .build();

            builder.riddle(frontendRiddleMap);

            return builder.build();
        }
        case at.escapedoom.spring.communication.data.model.StringCompareRiddleDTO stringCompare -> {
        }
        case null, default -> throw new IllegalStateException("Unexpected value: " + riddle);
        }

        throw new NotImplementedException("RiddleToFunctionMapper.riddleToFrontendFunction");
    }

    public static List<String> riddleToBackendFunction(
            at.escapedoom.spring.communication.data.model.CodingRiddleDTO riddle, EscapeRoomSolutionSubmition input) {

        String modified = input.getSolution().replaceFirst("(?<=public class Main \\{\\n)",
                getLectorCode(riddle.getCode(), CodingLanguage.valueOf(input.getLanguage().toString())));

        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(new StringReader(modified), "lectorCode");

        List<String> evaluatedTemplates = new ArrayList<>();

        riddle.getTestCases().forEach(testCase -> {

            StringWriter writer = new StringWriter();
            try {
                mustache.execute(writer, testCase.getInput()).flush();
                evaluatedTemplates.add(writer.toString());
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException("Cant parse template, Call a lector", e);
            }
        });

        return evaluatedTemplates;

    }

    public static void removeLectorCode(Map<String, String> map) {

        for (String key : map.keySet()) {
            CodingLanguage codingLanguage = CodingLanguage.fromValue(key);
            switch (codingLanguage) {
            case JAVA -> {
                String snippet = map.get(key).replaceAll("(?s)//region lector-code.*?//endregion\\s*", "");
                map.put(key, snippet);
            }
            case PYTHON -> {
                continue;
            }
            default -> throw new IllegalStateException("Unexpected value: " + codingLanguage);
            }

        }

    }

    public static String getLectorCode(Map<String, String> map, CodingLanguage codingLanguage) {

        Pattern pattern = Pattern.compile("(?s)//region lector-code\\s*(.*?)\\s*//endregion");
        Matcher matcher = pattern.matcher(map.get(codingLanguage.getValue()));
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        throw new IllegalStateException("Cant find region lector code in Java");

    }

}
