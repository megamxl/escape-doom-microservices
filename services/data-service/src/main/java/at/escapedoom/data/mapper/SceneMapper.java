package at.escapedoom.data.mapper;

import at.escapedoom.data.data.entity.*;
import at.escapedoom.data.rest.model.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = { NodeMapper.class })
public interface SceneMapper {

    SceneMapper INSTANCE = Mappers.getMapper(SceneMapper.class);

    @Mapping(source = "sceneId", target = "sceneId", qualifiedByName = "sceneUuidToString")
    @Mapping(source = "levelId", target = "levelId", qualifiedByName = "sceneUuidToString")
    @Mapping(source = "backgroundImageURI", target = "backgroundImageUri")
    SceneDTO toDTO(Scene scene);

    @Mapping(source = "sceneId", target = "sceneId", qualifiedByName = "sceneStringToUUID")
    @Mapping(source = "levelId", target = "levelId", qualifiedByName = "sceneStringToUUID")
    @Mapping(source = "backgroundImageUri", target = "backgroundImageURI")
    Scene toEntity(SceneDTO sceneDTO);

    @Mapping(source = "levelId", target = "levelId", qualifiedByName = "sceneUuidToString")
    SceneRequestDTO toSceneRequestDTO(SceneDTO sceneDTO);

    @Mapping(source = "levelId", target = "levelId", qualifiedByName = "sceneStringToUUID")
    Scene toEntity(SceneRequestDTO sceneRequest);

    @Named("sceneUuidToString")
    static String sceneUuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    @Named("sceneStringToUUID")
    static UUID sceneStringToUUID(String uuid) {
        return uuid != null && !uuid.isEmpty() ? UUID.fromString(uuid) : null;
    }

    default List<SceneDTO> toDTOList(List<Scene> scenes) {
        return scenes != null ? scenes.stream().map(this::toDTO).toList() : null;
    }

    default List<Scene> toEntityList(List<SceneDTO> sceneDTOs) {
        return sceneDTOs != null ? sceneDTOs.stream().map(this::toEntity).toList() : null;
    }
}
