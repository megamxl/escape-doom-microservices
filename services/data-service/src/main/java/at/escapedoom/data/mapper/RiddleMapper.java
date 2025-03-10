package at.escapedoom.data.mapper;

import at.escapedoom.data.data.entity.Riddle;
import at.escapedoom.data.rest.model.RiddleCreationRequestDTO;
import at.escapedoom.data.rest.model.RiddleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface RiddleMapper {

    RiddleMapper INSTANCE = Mappers.getMapper(RiddleMapper.class);

    @Mapping(source = "levelId", target = "levelId", qualifiedByName = "uuidToString")
    @Mapping(source = "riddleId", target = "riddleId", qualifiedByName = "uuidToString")
    RiddleDTO toDTO(Riddle riddle);

    @Mapping(source = "levelId", target = "levelId", qualifiedByName = "stringToUUID")
    @Mapping(source = "riddleId", target = "riddleId", qualifiedByName = "stringToUUID")
    Riddle toEntity(RiddleDTO riddleDTO);

    @Mapping(source = "levelId", target = "levelId", qualifiedByName = "stringToUUID")
    Riddle toEntity(RiddleCreationRequestDTO creationRequest);

    @Named("uuidToString")
    static String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    @Named("stringToUUID")
    static UUID stringToUUID(String uuid) {
        return uuid != null && !uuid.isEmpty() ? UUID.fromString(uuid) : null;
    }
}
