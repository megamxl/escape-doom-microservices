package at.escapedoom.data.mapper;

import at.escapedoom.data.data.entity.Level;
import at.escapedoom.data.data.entity.riddle.Riddle;
import at.escapedoom.data.rest.model.LevelDTO;
import at.escapedoom.data.rest.model.RiddleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = { NodeMapper.class })
public interface LevelMapper {

    @Mapping(source = "levelId", target = "levelId", qualifiedByName = "uuidToString")
    @Mapping(source = "templateId", target = "templateId", qualifiedByName = "uuidToString")
    @Mapping(source = "riddle", target = "riddle", qualifiedByName = "mapRiddleDTO")
    LevelDTO toDTO(Level level);

    @Mapping(source = "levelId", target = "levelId", qualifiedByName = "stringToUUID")
    @Mapping(source = "templateId", target = "templateId", qualifiedByName = "stringToUUID")
    @Mapping(source = "riddle", target = "riddle", qualifiedByName = "mapRiddle")
    Level toEntity(LevelDTO levelDTO);

    @Named("mapRiddle")
    default Riddle mapRiddle(RiddleDTO value) {
        return RiddleMapper.toEntity(value);
    }

    @Named("mapRiddleDTO")
    default RiddleDTO mapRiddleDTO(Riddle value) {
        return RiddleMapper.toDTO(value);
    }
}
