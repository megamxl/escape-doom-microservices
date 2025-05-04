package at.escapedoom.data.mapper;

import at.escapedoom.data.data.entity.Riddle;
import at.escapedoom.data.rest.model.RiddleCreationRequestDTO;
import at.escapedoom.data.rest.model.RiddleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface RiddleMapper {

    @Mapping(source = "riddleId", target = "riddleId", qualifiedByName = "uuidToString")
    @Mapping(source = "levelId", target = "levelId")
    RiddleDTO toDTO(Riddle riddle);

    @Mapping(source = "riddleId", target = "riddleId", qualifiedByName = "stringToUUID")
    Riddle toEntity(RiddleDTO riddleDTO);

    @Mapping(target = "level", ignore = true)
    Riddle toEntity(RiddleCreationRequestDTO creationRequest);

    @Named("uuidToString")
    default String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    @Named("stringToUUID")
    default UUID stringToUUID(String uuid) {
        return uuid != null && !uuid.isEmpty() ? UUID.fromString(uuid) : null;
    }
}
