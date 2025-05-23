package at.escapedoom.data.mapper;

import at.escapedoom.data.data.entity.Scene;
import at.escapedoom.data.rest.model.SceneDTO;
import at.escapedoom.data.rest.model.SceneRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = { NodeMapper.class })
public interface SceneMapper {

    @Mapping(source = "sceneId", target = "sceneId", qualifiedByName = "sceneUuidToString")
    @Mapping(source = "level.levelId", target = "levelId", qualifiedByName = "sceneUuidToString")
    SceneDTO toDTO(Scene scene);

    @Mapping(source = "sceneId", target = "sceneId", qualifiedByName = "sceneStringToUUID")
    @Mapping(source = "levelId", target = "levelId", qualifiedByName = "sceneStringToUUID")
    Scene toEntity(SceneDTO sceneDTO);

    @Mapping(source = "levelId", target = "levelId", qualifiedByName = "sceneUuidToString")
    SceneRequestDTO toSceneRequestDTO(SceneDTO sceneDTO);

    @Mapping(source = "levelId", target = "levelId", qualifiedByName = "sceneStringToUUID")
    @Mapping(source = "backgroundImageUri", target = "backgroundImageUri")
    Scene toEntity(SceneRequestDTO sceneRequest);

    @Named("sceneUuidToString")
    default String sceneUuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    @Named("sceneStringToUUID")
    default UUID sceneStringToUUID(String uuid) {
        return uuid != null && !uuid.isEmpty() ? UUID.fromString(uuid) : null;
    }

    default List<SceneDTO> toDTOList(List<Scene> scenes) {
        return scenes != null ? scenes.stream().map(this::toDTO).toList() : null;
    }

    default List<Scene> toEntityList(List<SceneDTO> sceneDTOs) {
        return sceneDTOs != null ? sceneDTOs.stream().map(this::toEntity).toList() : null;
    }
}
