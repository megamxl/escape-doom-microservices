package at.escapedoom.data.mapper;

import at.escapedoom.data.data.entity.Level;
import at.escapedoom.data.rest.model.LevelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface LevelMapper {

    @Mapping(source = "levelId", target = "levelId", qualifiedByName = "uuidToString")
    @Mapping(source = "templateId", target = "templateId", qualifiedByName = "uuidToString")
    LevelDTO toDTO(Level level);

    @Mapping(source = "levelId", target = "levelId", qualifiedByName = "stringToUUID")
    @Mapping(source = "templateId", target = "templateId", qualifiedByName = "stringToUUID")
    Level toEntity(LevelDTO levelDTO);

    @Named("uuidToString")
    default String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    @Named("stringToUUID")
    default UUID stringToUUID(String uuid) {
        return uuid != null && !uuid.isEmpty() ? UUID.fromString(uuid) : null;
    }
}
