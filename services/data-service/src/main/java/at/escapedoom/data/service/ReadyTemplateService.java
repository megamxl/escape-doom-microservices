package at.escapedoom.data.service;

import at.escapedoom.data.rest.model.*;
import at.escapedoom.data.utils.KeyCloakUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReadyTemplateService {

    public ReadyEscapeRoomDTO getReadyEscapeRoom() {
        return generateMockEscapeRoom();
    }

    private ReadyEscapeRoomDTO generateMockEscapeRoom() {
        String templateId = UUID.randomUUID().toString();
        String levelId = UUID.randomUUID().toString();
        String sceneId = UUID.randomUUID().toString();

        NodeDTO node = createNode(sceneId);
        SceneDTO scene = createScene(sceneId, levelId, List.of(node));
        RiddleDTO riddle = createRiddle(levelId);

        LevelDTO level = LevelDTO.builder()
                .levelId(levelId)
                .templateId(templateId)
                .name("Level 1: Library")
                .levelSequence(1)
                .scenes(List.of(scene))
                .riddle(riddle)
                .build();

        ReadyLevelDTO readyLevel = ReadyLevelDTO.builder()
                .level(level)
                .scenes(List.of(ReadySceneDTO.builder()
                        .scene(scene)
                        .nodes(List.of(node))
                        .build()))
                .build();

        TemplateDTO templateDTO = TemplateDTO.builder()
                .templateId(templateId)
                .userId(KeyCloakUtils.getUserId().toString())
                .name("Test Escape Room")
                .description("This is a escape room.")
                .levels(List.of(level))
                .build();

        return ReadyEscapeRoomDTO.builder()
                .template(templateDTO)
                .levels(List.of(readyLevel))
                .build();
    }

    private NodeDTO createNode(String sceneId) {
        return NodeDTO.builder()
                .nodeId(UUID.randomUUID())
                .sceneId(UUID.fromString(sceneId))
                .nodeType(NodeType.STORY)
                .position(PositionDTO.builder().leftPercentage(0.475).topPercentage(0.3).build())
                .nodeInfo(NodeInfoDTO.builder()
                        .title("An old friend")
                        .description("This photo looks familiar...")
                        .imageURI("https://asset.museum-digital.org/brandenburg/images/202004/gaius-julius-caesar-100-44-v-chr-38964.jpg")
                        .build())
                .build();
    }

    private SceneDTO createScene(String sceneId, String levelId, List<NodeDTO> nodes) {
        return SceneDTO.builder()
                .sceneId(sceneId)
                .sceneSequence(1)
                .levelId(levelId)
                .nodes(nodes)
                .backgroundImageUri("https://imgur.com/fICDEUI")
                .name("Computer Lab")
                .build();
    }

    private RiddleDTO createRiddle(String levelId) {
        return RiddleDTO.builder()
                .riddleId(UUID.randomUUID().toString())
                .levelId(levelId)
                .functionSignature("/**\n" +
                        "* @param input is a List of Lists of Booleans \n" +
                        "*              Example \n" +
                        "*              [\n" +
                        "*                  [true,true,false,true],\n" +
                        "*                  [false,true,false,true,true,true],\n" +
                        "*                  [true,true],\n" +
                        "*              ]\n" +
                        "* @return the current Floor\n" +
                        "*/\n" +
                        "public static int solve(List<List<Boolean>> input) {\n\n}")
                .input("public static List<List<Boolean>> listOfBinary = List.of(\n" +
                        "List.of(true, false, false, false, true, true),\n" +
                        "List.of(true, true, false, false),\n" +
                        "List.of(true,true,false,true,false,true,true,true,false),\n" +
                        "List.of(true,true,false,true),\n" +
                        "List.of(true,true,false,true,true),\n" +
                        "List.of(true,false,false,true,false,true,false,true,false,false),\n" +
                        "List.of(true,false,false,false,false,false,false,false),\n" +
                        "List.of(true,false,false,true,false,false,false,false,true),\n" +
                        "List.of(true,false,false,false,true,true,true,true,false),\n" +
                        "List.of(true,false,false,false,false,true,true,true,true,true,true)\n" +
                        ");")
                .expectedOutput("-1")
                .variableName("listOfBinary")
                .language(CodingLanguage.JAVA)
                .build();
    }
}
